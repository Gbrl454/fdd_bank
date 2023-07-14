package dtec.bank.api.service;

import dtec.bank.api.domain.agencia.AgenciaRepository;
import dtec.bank.api.domain.conta.Conta;
import dtec.bank.api.domain.conta.ContaRepository;
import dtec.bank.api.domain.conta.DadosCadastroConta;
import dtec.bank.api.domain.conta.DadosDetalhamentoConta;
import dtec.bank.api.domain.usuario.UsuarioRepository;
import dtec.bank.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Autowired
    ContaRepository contaRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public DadosDetalhamentoConta cadastrar (DadosCadastroConta dados) {
        if (!agenciaRepository.existsById(dados.idAgencia())) {
            throw new ValidacaoException("ID de Agência informado inexistente!");
        }

        var agencia = agenciaRepository.getReferenceById(dados.idAgencia());

        if (!usuarioRepository.existsById(dados.idUsuario())) {
            throw new ValidacaoException("ID de Usuário informado inexistente!");
        }

        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());

        var conta = new Conta();

        switch (dados.tipo()) {
            case NORMAL -> {
                if (dados.cartao_de_credito() != null && dados.cartao_de_credito()) {
                    throw new ValidacaoException("Contas do Tipo NORMAL não podem possuir Cartão de Crédito!");
                }

                if (dados.lis() != null && dados.lis()) {
                    throw new ValidacaoException("Contas do Tipo NORMAL não podem possuir Cheque Especial (LIS)!");
                }

                // conta = new Conta(null, agencia, usuario, dados.saldo(), dados.tipo(), false, new BigDecimal(0), false, new BigDecimal(0));
            }
            case ESPECIAL -> {
                if (dados.cartao_de_credito() != null && dados.cartao_de_credito() && dados.saldo_cartao_de_credito() == null) {
                    throw new ValidacaoException("Contas do Tipo ESPECIAL devem informar o Saldo do Cartão de Crédito!");
                }

                if (dados.lis() != null && dados.lis()) {
                    throw new ValidacaoException("Contas do Tipo ESPECIAL não podem possuir Cheque Especial (LIS)!");
                }

                // conta = new Conta(null, agencia, usuario, dados.saldo(), dados.tipo(), dados.cartao_de_credito(), dados.saldo_cartao_de_credito(), false, new BigDecimal(0));
            }
            case PREMIUM -> {
                if (dados.cartao_de_credito() && dados.saldo_cartao_de_credito() == null) {
                    throw new ValidacaoException("Contas do Tipo PREMIUM devem informar o Saldo do Cartão de Crédito!");
                }

                if (dados.lis() && dados.saldo_lis() == null) {
                    throw new ValidacaoException("Contas do Tipo PREMIUM devem informar o Saldo do Cheque Especial (LIS)!");
                }

                // conta = new Conta(null, agencia, usuario, dados.saldo(), dados.tipo(), dados.cartao_de_credito(), dados.saldo_cartao_de_credito(), dados.lis(), dados.saldo_lis());
            }
        }

        contaRepository.save(conta);
        return new DadosDetalhamentoConta(conta);
    }
}
