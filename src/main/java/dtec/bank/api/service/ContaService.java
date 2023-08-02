package dtec.bank.api.service;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.UsuarioRepository;
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
        long saldo_cartao_de_credito = 0L;
        boolean lis = false;
        long saldo_lis = 0L;

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
                            saldo_cartao_de_credito = (long) (dados.saldo_cartao_de_credito() * dados.moeda().getMultiplicador());
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
                            saldo_cartao_de_credito = (long) (dados.saldo_cartao_de_credito() * dados.moeda().getMultiplicador());
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
                            saldo_lis = (long) (dados.saldo_lis() * dados.moeda().getMultiplicador());
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

        Long saldo = (long) (dados.saldo() * dados.moeda().getMultiplicador());

        var conta = new Conta(null, agencia, usuario, dados.moeda(), saldo, dados.tipo(), cartao_de_credito, saldo_cartao_de_credito, lis, saldo_lis);
        contaRepository.save(conta);
        return new DadosDetalhamentoConta(conta);
    }
}
