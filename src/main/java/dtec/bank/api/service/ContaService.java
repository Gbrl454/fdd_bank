package dtec.bank.api.service;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.UsuarioRepository;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    BankLocateResolver locateResolver;
    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    AgenciaRepository agenciaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public DadosDetalhamentoConta cadastrar(DadosCadastroConta dados) {
        if (!agenciaRepository.existsById(dados.idAgencia())) {
            throw new ValidacaoException(get("agencia.id.notexist"));
        }

        var agencia = agenciaRepository.getReferenceById(dados.idAgencia());

        if (!usuarioRepository.existsById(dados.idUsuario())) {
            throw new ValidacaoException(get("usuario.id.notexist"));
        }

        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());

        boolean cartao_de_credito = false;
        long saldo_cartao_de_credito = 0L;
        boolean lis = false;
        long saldo_lis = 0L;

        switch (dados.tipo()) {
            case NORMAL -> {
                if ((dados.cartao_de_credito() != null && dados.cartao_de_credito()) || (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0)) {
                    throw new ValidacaoException(get("conta.normal.notown.creditcard"));
                }

                if ((dados.lis() != null && dados.lis()) || (dados.saldo_lis() != null && dados.saldo_lis() > 0)) {
                    throw new ValidacaoException(get("conta.normal.notown.lis"));
                }
            }
            case ESPECIAL -> {
                if (dados.cartao_de_credito() != null) {
                    cartao_de_credito = dados.cartao_de_credito();

                    if (cartao_de_credito) {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            saldo_cartao_de_credito = (long) (dados.saldo_cartao_de_credito() * dados.moeda().getMultiplicador());
                        } else {
                            throw new ValidacaoException(get("saldo.creditcard.uninformed"));
                        }
                    } else {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            throw new ValidacaoException(get("saldo.creditcard.informonlyhave"));
                        }
                    }
                } else {
                    if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                        throw new ValidacaoException(get("saldo.creditcard.informonlyhave"));
                    }
                }

                if ((dados.lis() != null && dados.lis()) || (dados.saldo_lis() != null && dados.saldo_lis() > 0)) {
                    throw new ValidacaoException(get("conta.especial.notown.lis"));
                }
            }
            case PREMIUM -> {
                if (dados.cartao_de_credito() != null) {
                    cartao_de_credito = dados.cartao_de_credito();

                    if (cartao_de_credito) {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            saldo_cartao_de_credito = (long) (dados.saldo_cartao_de_credito() * dados.moeda().getMultiplicador());
                        } else {
                            throw new ValidacaoException(get("saldo.creditcard.informwhenhave"));
                        }
                    } else {
                        if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                            throw new ValidacaoException(get("saldo.creditcard.informonlyhave"));
                        }
                    }
                } else {
                    if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                        throw new ValidacaoException(get("saldo.creditcard.informonlyhave"));
                    }
                }

                if (dados.lis() != null) {
                    lis = dados.lis();

                    if (lis) {
                        if (dados.saldo_lis() != null && dados.saldo_lis() > 0) {
                            saldo_lis = (long) (dados.saldo_lis() * dados.moeda().getMultiplicador());
                        } else {
                            throw new ValidacaoException(get("saldo.lis.informwhenhave"));
                        }
                    } else {
                        if (dados.saldo_lis() != null && dados.saldo_lis() > 0) {
                            throw new ValidacaoException(get("saldo.lis.informonlyhave"));
                        }
                    }
                } else {
                    if (dados.saldo_lis() != null && dados.saldo_lis() > 0) {
                        throw new ValidacaoException(get("saldo.lis.informonlyhave"));
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
