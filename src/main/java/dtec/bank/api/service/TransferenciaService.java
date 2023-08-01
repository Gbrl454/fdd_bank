package dtec.bank.api.service;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.Transferencia;
import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
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


    public DadosDetalhamentoTransferencia cadastrar (DadosCadastroTransferencia dados) {
//        if (dados.idOConta() != null && !contaRepository.existsById(dados.idOConta())) {
//            throw new ValidacaoException(ErrorMessage.idContaOrigemNotExist);
//        }
//
//        if (dados.idDConta() != null && !contaRepository.existsById(dados.idDConta())) {
//            throw new ValidacaoException(ErrorMessage.idContaDestinoNotExist);
//        }
//
//        if (dados.idOConta().equals(dados.idDConta())) {
//            throw new ValidacaoException(ErrorMessage.idContaOrigemDestinatarioEquals);
//        }
//
//        Conta oConta = contaRepository.getReferenceById(dados.idOConta());
//        Conta dConta = contaRepository.getReferenceById(dados.idDConta());
//        long valor = (long) ((dados.valor() * oConta.getMoeda().getMultiplicador())*100);
//
//       if(transferir(oConta, dConta, valor)) {
//           var transferencia = new Transferencia(null, oConta, dConta, saldo);
//       }else{
//           var transferencia = new Transferencia(null, oConta, dConta, saldo,"Saldo insuficiente");
//       }
//
//
//
//        transferenciaRepository.save(transferencia);
//
//        return new DadosDetalhamentoTransferencia(transferencia);
        return null;
    }

//    private boolean transferir (Conta oConta, Conta dConta, Double valor) {
//        if (removerSaldo(oConta, valor)) {
//            dConta.setSaldo(dConta.getSaldo() + valor);
//            return true;
//        }
//        return false;
//    }
//
//    private boolean removerSaldo (Conta oConta, Long valor) {
//        if (oConta.getSaldo() >= valor) {
//            oConta.setSaldo(oConta.getSaldo() - valor);
//            return true;
//        }
//
//        if (oConta.getLis() && (oConta.getSaldo_lis() + oConta.getSaldo()) >= valor) {
//            valor -= oConta.getSaldo();
//            oConta.setSaldo(0.0);
//            oConta.setSaldo_lis(oConta.getSaldo_lis() - valor);
//            return true;
//        }
//
//        if (oConta.getCartao_de_credito() && (oConta.getSaldo_cartao_de_credito() + oConta.getSaldo()) >= valor) {
//            valor -= oConta.getSaldo();
//            oConta.setSaldo(0.0);
//            oConta.setSaldo_cartao_de_credito(oConta.getSaldo_cartao_de_credito() - valor);
//            return true;
//        }
//
//        return false;
//    }
}
