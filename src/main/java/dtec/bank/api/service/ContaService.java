package dtec.bank.api.service;

import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.entity.Conta;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.repository.UsuarioRepository;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.utils.ErrorMessage;
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
            throw new ValidacaoException(ErrorMessage.idAgenciaNotExist);
        }

        var agencia = agenciaRepository.getReferenceById(dados.idAgencia());

        if (!usuarioRepository.existsById(dados.idUsuario())) {
            throw new ValidacaoException(ErrorMessage.idUsuarioNotExist);
        }

        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());

        boolean cartao_de_credito = false;
        Double saldo_cartao_de_credito = 0.0;
        boolean lis = false;
        Double saldo_lis = 0.0;

        switch (dados.tipo()) {
            case NORMAL -> {
                if ((dados.cartao_de_credito() != null && dados.cartao_de_credito()) || (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0)) {
                    throw new ValidacaoException(ErrorMessage.accountNormalCantHaveCreditCard);
                }

                if ((dados.lis() != null && dados.lis()) || (dados.saldo_lis() != null && dados.saldo_lis() > 0)) {
                    throw new ValidacaoException(ErrorMessage.accountNormalCantHaveLIS);
                }
            }
            case ESPECIAL -> {
                if (dados.cartao_de_credito() != null) {
                    cartao_de_credito = dados.cartao_de_credito();

                    if (cartao_de_credito) {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            saldo_cartao_de_credito = dados.saldo_cartao_de_credito();
                        } else {
                            throw new ValidacaoException(ErrorMessage.balanceCreditCardUninformed);
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
                    throw new ValidacaoException(ErrorMessage.accountEspecialCantHaveLIS);
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
