package dtec.bank.api.service;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.Transferencia;
import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
import dtec.bank.api.exception.TransferenciaException;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.TransferenciaRepository;
import dtec.bank.api.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    @Autowired
    BancoRepository bancoRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransferenciaRepository transferenciaRepository;

    public DadosDetalhamentoTransferencia cadastrar(DadosCadastroTransferencia dados) {
        if (dados.idOConta() != null && !contaRepository.existsById(dados.idOConta())) {
            throw new ValidacaoException(ErrorMessage.idContaOrigemNotExist);
        }

        if (dados.idDConta() != null && !contaRepository.existsById(dados.idDConta())) {
            throw new ValidacaoException(ErrorMessage.idContaDestinoNotExist);
        }

        if (dados.idOConta().equals(dados.idDConta())) {
            throw new ValidacaoException(ErrorMessage.idContaOrigemDestinatarioEquals);
        }

        Conta oConta = contaRepository.getReferenceById(dados.idOConta());
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

    private void removerSaldo(Conta oConta, Long valor) throws TransferenciaException {
        if (oConta.getSaldo() > valor) {
            oConta.retirarSaldo(valor);
            return;
        }

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

        throw new TransferenciaException(ErrorMessage.saldoTransferenciaInsuficiente);
    }
}
