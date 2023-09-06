package dtec.bank.api.service;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.Transferencia;
import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoMoeda;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
import dtec.bank.api.entity.dto.DadosListagemTransferencia;
import dtec.bank.api.exception.TransferenciaException;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.TransferenciaRepository;
import dtec.bank.api.utils.BankLocateResolver;
import dtec.bank.api.utils.TipoConta;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    BankLocateResolver locateResolver;
    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Autowired
    BancoRepository bancoRepository;
    @Autowired
    AgenciaRepository agenciaRepository;
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    TransferenciaRepository transferenciaRepository;

    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public DadosDetalhamentoTransferencia cadastrar(DadosCadastroTransferencia dados) {
        return cadastrar(dados, null);
    }

    public DadosDetalhamentoTransferencia cadastrar(DadosCadastroTransferencia dados, Usuario logado) {
        Long idOConta = (dados.idOConta() == null) ? logado.getId() : dados.idOConta();

        if ((idOConta != null && !contaRepository.existsById(idOConta)))
            throw new ValidacaoException(get("conta.origem.notexist"));

        if (!contaRepository.existsById(dados.idDConta()))
            throw new ValidacaoException(get("conta.destino.notexist"));

        if (idOConta != null && idOConta.equals(dados.idDConta()))
            throw new ValidacaoException(get("conta.origemdestinoequals"));

        Conta oConta = contaRepository.getReferenceById(idOConta);
        Conta dConta = contaRepository.getReferenceById(dados.idDConta());
        long valor = (long) (dados.valor() * oConta.getMoeda().getMultiplicador());

        Transferencia transferencia;

        try {
            transferencia = new Transferencia(null, oConta, dConta, valor);
            removerSaldo(oConta, valor);
            dConta.adicionarSaldo(valor);
        } catch (TransferenciaException e) {
            transferencia = new Transferencia(null, oConta, dConta, valor, e.getMessage());
        }

        transferenciaRepository.save(transferencia);
        return new DadosDetalhamentoTransferencia(transferencia);
    }

    protected void removerSaldo(Conta oConta, Long valor) throws TransferenciaException {
        if (oConta.getSaldo() > valor) {
            oConta.retirarSaldo(valor);
            return;
        }

        if (oConta.getTipo() == TipoConta.ESPECIAL && ((oConta.getSaldo() + oConta.getSaldo_cartao_de_credito()) > valor)) {
            valor -= oConta.getSaldo();
            oConta.zerarSaldo();
            oConta.retirarSaldo_cartao_de_credito(valor);
            return;
        }

        if (oConta.getTipo() == TipoConta.PREMIUM) {
            if ((oConta.getSaldo() + oConta.getSaldo_lis()) > valor) {
                valor -= oConta.getSaldo();
                oConta.zerarSaldo();
                oConta.retirarSaldo_lis(valor);
                return;
            }

            if ((oConta.getSaldo() + oConta.getSaldo_lis() + oConta.getSaldo_cartao_de_credito()) > valor) {
                valor -= oConta.getSaldo();
                oConta.zerarSaldo();
                valor -= oConta.getSaldo_lis();
                oConta.zerarSaldo_lis();
                oConta.retirarSaldo_cartao_de_credito(valor);
                return;
            }
        }

        throw new TransferenciaException(get("saldo.transferencia.insuficiente"));
    }

    public List<DadosListagemTransferencia> listar(Usuario logado) {
        return transferenciaRepository
                .findAllByIdOConta(logado.getId())
                .stream()
                .map(ddt -> new DadosListagemTransferencia(
                        ddt.getId(),
                        ddt.getSucesso(),
                        ddt.getMotivo(),
                        ((double) ddt.getValor() / ddt.getOConta().getMoeda().getMultiplicador()),
                        contaRepository.getReferenceById(ddt.getOConta().getId()).getUsuario().getNome(),
                        contaRepository.getReferenceById(ddt.getDConta().getId()).getUsuario().getNome(),
                        new DadosDetalhamentoMoeda(ddt.getOConta().getMoeda()),
                        ddt.getHorario_tranferencia()
                ))
                .toList();
    }
}
