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

        boolean cartao_de_credito = false;
        Double saldo_cartao_de_credito = 0.0;
        boolean lis = false;
        Double saldo_lis = 0.0;

        switch (dados.tipo()) {
            case NORMAL -> {
                if ((dados.cartao_de_credito() != null && dados.cartao_de_credito()) || (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0)) {
                    throw new ValidacaoException("Contas do Tipo NORMAL não podem possuir Cartão de Crédito!");
                }

                if ((dados.lis() != null && dados.lis()) || (dados.saldo_lis() != null && dados.saldo_lis() > 0)) {
                    throw new ValidacaoException("Contas do Tipo NORMAL não podem possuir Cheque Especial (LIS)!");
                }
            }
            case ESPECIAL -> {
                if (dados.cartao_de_credito() != null) {
                    cartao_de_credito = dados.cartao_de_credito();

                    if (cartao_de_credito) {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            saldo_cartao_de_credito = dados.saldo_cartao_de_credito();
                        } else {
                            throw new ValidacaoException("Saldo do Cartão de Crédito deve ser informado quando a Conta possui Cartão de Crédito!");
                        }
                    } else {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            throw new ValidacaoException("Saldo do Cartão de Crédito somente deve ser informado quando a Conta possui Cartão de Crédito!");
                        }
                    }
                } else {
                    if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                        throw new ValidacaoException("Saldo do Cartão de Crédito somente deve ser informado quando a Conta possui Cartão de Crédito!");
                    }
                }

                if ((dados.lis() != null && dados.lis()) || (dados.saldo_lis() != null && dados.saldo_lis() > 0)) {
                    throw new ValidacaoException("Contas do Tipo ESPECIAL não podem possuir Cheque Especial (LIS)!");
                }
            }
            case PREMIUM -> {
                if (dados.cartao_de_credito() != null) {
                    cartao_de_credito = dados.cartao_de_credito();

                    if (cartao_de_credito) {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            saldo_cartao_de_credito = dados.saldo_cartao_de_credito();
                        } else {
                            throw new ValidacaoException("Saldo do Cartão de Crédito deve ser informado quando a Conta possui Cartão de Crédito!");
                        }
                    } else {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            throw new ValidacaoException("Saldo do Cartão de Crédito somente deve ser informado quando a Conta possui Cartão de Crédito!");
                        }
                    }
                } else {
                    if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                        throw new ValidacaoException("Saldo do Cartão de Crédito somente deve ser informado quando a Conta possui Cartão de Crédito!");
                    }
                }

                if (dados.lis() != null) {
                    lis = dados.lis();

                    if (lis) {
                        if (dados.saldo_lis() != null && dados.saldo_lis() > 0) {
                            saldo_lis = dados.saldo_lis();
                        } else {
                            throw new ValidacaoException("Saldo do Cheque Especial (LIS) deve ser informado quando a Conta possui Cheque Especial (LIS)!");
                        }
                    } else {
                        if (dados.saldo_lis() != null && dados.saldo_lis() > 0) {
                            throw new ValidacaoException("Saldo do Cheque Especial (LIS) somente deve ser informado quando a Conta possui Cheque Especial (LIS)!");
                        }
                    }
                } else {
                    if (dados.saldo_lis() != null && dados.saldo_lis() > 0) {
                        throw new ValidacaoException("Saldo do Cheque Especial (LIS) somente deve ser informado quando a Conta possui Cheque Especial (LIS)!");
                    }
                }
            }
        }

        var conta = new Conta(null, agencia, usuario, dados.saldo(), dados.tipo(), cartao_de_credito, saldo_cartao_de_credito, lis, saldo_lis);

        contaRepository.save(conta);
        return new DadosDetalhamentoConta(conta);
    }
}
