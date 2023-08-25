package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Agencia;

public record DadosDetalhamentoAgencia(Long id, String nome, Long idbanco) {
    public DadosDetalhamentoAgencia(Agencia agencia) {
        this(agencia.getId(), agencia.getNome(), agencia.getBanco().getId());
    }
}
