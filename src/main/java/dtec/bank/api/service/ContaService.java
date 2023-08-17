package dtec.bank.api.service;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.UsuarioRepository;
import dtec.bank.api.utils.BankLocateResolver;
import dtec.bank.api.utils.TipoConta;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Autowired
    private BankLocateResolver locateResolver;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private AgenciaRepository agenciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public DadosDetalhamentoConta cadastrar(DadosCadastroConta dados) {
        final String saldoCreditCardInformOnlyHave = get("saldo.creditcard.informonlyhave");

        if (!agenciaRepository.existsById(dados.idAgencia())) {
            throw new ValidacaoException(get("agencia.id.notexist"));
        }

        var agencia = agenciaRepository.getReferenceById(dados.idAgencia());

        if (!usuarioRepository.existsById(dados.idUsuario())) {
            throw new ValidacaoException(get("usuario.id.notexist"));
        }

        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());

        boolean cartaoCredito = false;
        long saldoCartaoCredito = 0L;
        boolean lis = false;
        long saldoLis = 0L;

        if (dados.tipo() == TipoConta.NORMAL) {
            if ((dados.cartao_de_credito() != null && dados.cartao_de_credito()) || (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0)) {
                throw new ValidacaoException(get("conta.normal.notown.creditcard"));
            }

            if ((dados.lis() != null && dados.lis()) || (dados.saldo_lis() != null && dados.saldo_lis() > 0)) {
                throw new ValidacaoException(get("conta.normal.notown.lis"));
            }
        }

        if (dados.tipo() == TipoConta.ESPECIAL || dados.tipo() == TipoConta.PREMIUM) {
            if (dados.cartao_de_credito() != null) {
                cartaoCredito = dados.cartao_de_credito();

                if (cartaoCredito) {
                    if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                        saldoCartaoCredito = (long) (dados.saldo_cartao_de_credito() * dados.moeda().getMultiplicador());
                    } else {
                        throw new ValidacaoException(get("saldo.creditcard.informwhenhave"));
                    }
                } else {
                    if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                        throw new ValidacaoException(saldoCreditCardInformOnlyHave);
                    }
                }
            } else {
                if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0) {
                    throw new ValidacaoException(saldoCreditCardInformOnlyHave);
                }
            }
        }

        if (dados.tipo() == TipoConta.ESPECIAL && ((dados.lis() != null && dados.lis()) || (dados.saldo_lis() != null && dados.saldo_lis() > 0))) {
            throw new ValidacaoException(get("conta.especial.notown.lis"));
        }

        if (dados.tipo() == TipoConta.PREMIUM) {
            if (dados.lis() != null) {
                lis = dados.lis();

                if (lis) {
                    if (dados.saldo_lis() != null && dados.saldo_lis() > 0) {
                        saldoLis = (long) (dados.saldo_lis() * dados.moeda().getMultiplicador());
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

        Long saldo = (long) (dados.saldo() * dados.moeda().getMultiplicador());

        var conta = new Conta(null, agencia, usuario, dados.moeda(), saldo, dados.tipo(), cartaoCredito, saldoCartaoCredito, lis, saldoLis);
        contaRepository.save(conta);
        return new DadosDetalhamentoConta(conta);
    }
}
