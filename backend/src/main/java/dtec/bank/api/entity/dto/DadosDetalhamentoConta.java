package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.utils.TipoConta;

public record DadosDetalhamentoConta(
        Long id, TipoConta tipo,
        Double saldo, Boolean cartaoDeCredito, Double saldoCartaoDeCredito, Boolean lis, Double saldoLis,
        DadosDetalhamentoAgencia agencia, DadosDetalhamentoBanco banco, DadosDetalhamentoUsuario usuario
) {
    public DadosDetalhamentoConta(Conta conta) {
        this(
                conta.getId(), conta.getTipo(),
                (double) (conta.getSaldo() / conta.getBanco().getPais().getMoeda().getMultiplicador()), conta.getCartaoDeCredito(),
                (double) (conta.getSaldoCartaoDeCredito() / conta.getBanco().getPais().getMoeda().getMultiplicador()), conta.getLis(),
                (double) (conta.getSaldoLis() / conta.getBanco().getPais().getMoeda().getMultiplicador()),
                new DadosDetalhamentoAgencia(conta.getAgencia()), new DadosDetalhamentoBanco(conta.getBanco()), new DadosDetalhamentoUsuario(conta.getUsuario())
        );
    }
}
