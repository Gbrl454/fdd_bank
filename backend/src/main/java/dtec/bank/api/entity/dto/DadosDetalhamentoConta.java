package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.utils.TipoConta;

public record DadosDetalhamentoConta(
        Long id, TipoConta tipo,
        Long saldo, Boolean cartaoDeCredito, Long saldocartaoDeCredito, Boolean lis, Long saldoLis,
        DadosDetalhamentoAgencia agencia, DadosDetalhamentoBanco banco, DadosDetalhamentoUsuario usuario
) {
    public DadosDetalhamentoConta(Conta conta) {
        this(
                conta.getId(), conta.getTipo(),
                conta.getSaldo(), conta.getCartaoDeCredito(), conta.getSaldoCartaoDeCredito(), conta.getLis(), conta.getSaldoLis(),
                new DadosDetalhamentoAgencia(conta.getAgencia()), new DadosDetalhamentoBanco(conta.getBanco()), new DadosDetalhamentoUsuario(conta.getUsuario())
        );
    }
}
