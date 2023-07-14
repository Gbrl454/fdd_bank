package dtec.bank.api.domain.conta;

import dtec.bank.api.domain.agencia.Agencia;
import dtec.bank.api.domain.usuario.Usuario;

import java.math.BigDecimal;

public record DadosDetalhamentoConta(Long id, Agencia agencia, Usuario usuario, BigDecimal saldo, TipoConta tipo,
                                     Boolean cartao_de_credito, BigDecimal saldo_cartao_de_credito, Boolean lis,
                                     BigDecimal saldo_lis) {
}
