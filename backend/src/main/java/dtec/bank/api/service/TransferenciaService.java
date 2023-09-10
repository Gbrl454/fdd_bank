package dtec.bank.api.service;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.Transferencia;
import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
import dtec.bank.api.exception.TransferenciaException;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.*;
import dtec.bank.api.utils.BankLocateResolver;
import dtec.bank.api.utils.TipoConta;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

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
    UsuarioRepository usuarioRepository;
    @Autowired
    TransferenciaRepository transferenciaRepository;

    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public DadosDetalhamentoTransferencia register(DadosCadastroTransferencia dados) {
        return register(dados, null);
    }

    public DadosDetalhamentoTransferencia register(DadosCadastroTransferencia dados, Usuario logado) {
        Long idOConta;
        if (dados.idOConta() == null) {
            List<DadosDetalhamentoConta> listConta = contaRepository.findAllByIdUsuario(logado.getId());

            if (listConta.isEmpty()) throw new ValidacaoException(get("conta.list.empty.byidusuario"));
            if (listConta.size() > 1) throw new ValidacaoException(get("conta.list.several"));

            idOConta = listConta.get(0).id();
        } else idOConta = dados.idOConta();

        if ((idOConta != null && !contaRepository.existsById(idOConta)))
            throw new ValidacaoException(get("conta.origem.notexist"));

        if (!contaRepository.existsById(dados.idDConta()))
            throw new ValidacaoException(get("conta.destino.notexist"));

        if (idOConta != null && idOConta.equals(dados.idDConta()))
            throw new ValidacaoException(get("conta.origemdestinoequals"));

        Conta oConta = contaRepository.getReferenceById(idOConta);
        Conta dConta = contaRepository.getReferenceById(dados.idDConta());
        long valor = (long) (dados.valor() * oConta.getBanco().getPais().getMoeda().getMultiplicador());

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

        if (oConta.getTipo() == TipoConta.ESPECIAL && ((oConta.getSaldo() + oConta.getSaldoCartaoDeCredito()) > valor)) {
            valor -= oConta.getSaldo();
            oConta.zerarSaldo();
            oConta.retirarSaldoCartaoCeCredito(valor);
            return;
        }

        if (oConta.getTipo() == TipoConta.PREMIUM) {
            if ((oConta.getSaldo() + oConta.getSaldoLis()) > valor) {
                valor -= oConta.getSaldo();
                oConta.zerarSaldo();
                oConta.retirarSaldoLis(valor);
                return;
            }

            if ((oConta.getSaldo() + oConta.getSaldoLis() + oConta.getSaldoCartaoDeCredito()) > valor) {
                valor -= oConta.getSaldo();
                oConta.zerarSaldo();
                valor -= oConta.getSaldoLis();
                oConta.zerarSaldoLis();
                oConta.retirarSaldoCartaoCeCredito(valor);
                return;
            }
        }

        throw new TransferenciaException(get("saldo.transferencia.insuficiente"));
    }

    public List<DadosDetalhamentoTransferencia> listAll(Usuario logado) {
        List<DadosDetalhamentoTransferencia> list = transferenciaRepository.findAll().stream().map(DadosDetalhamentoTransferencia::new).toList();

        if (list.isEmpty()) throw new ValidacaoException(get("transferencia.list.empty.all"));

        return list;
    }

    public DadosDetalhamentoTransferencia transferenciaById(Long idTransferencia) {
        if (!transferenciaRepository.existsById(idTransferencia))
            throw new ValidacaoException(get("transferencia.id.notexist"));

        return new DadosDetalhamentoTransferencia(transferenciaRepository.getReferenceById(idTransferencia));
    }
}
