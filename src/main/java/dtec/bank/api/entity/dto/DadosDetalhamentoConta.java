package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.utils.TipoConta;

public record DadosDetalhamentoConta(Long id, Long idAgencia, Long idUsuario, DadosDetalhamentoMoeda moeda, Long saldo,
                                     TipoConta tipo, Boolean cartao_de_credito, Long saldo_cartao_de_credito,
                                     Boolean lis, Long saldo_lis) {
    public DadosDetalhamentoConta (Conta conta) {
        this(conta.getId(), conta.getAgencia().getId(), conta.getUsuario().getId(), new DadosDetalhamentoMoeda(conta.getMoeda()), conta.getSaldo(), conta.getTipo(), conta.getCartao_de_credito(), conta.getSaldo_cartao_de_credito(), conta.getLis(), conta.getSaldo_lis());
    }
}
