package dtec.bank.api.domain.conta;

import java.math.BigDecimal;

public record DadosDetalhamentoConta(Long id, Long idAgencia, Long idUsuario, BigDecimal saldo, TipoConta tipo,
                                     Boolean cartao_de_credito, BigDecimal saldo_cartao_de_credito, Boolean lis,
                                     BigDecimal saldo_lis) {
    public DadosDetalhamentoConta (Conta conta) {
        this(conta.getId(), conta.getAgencia().getId(), conta.getUsuario().getId(), conta.getSaldo(), conta.getTipo(), conta.getCartao_de_credito(), conta.getSaldo_cartao_de_credito(), conta.getLis(), conta.getSaldo_lis());
    }
}
