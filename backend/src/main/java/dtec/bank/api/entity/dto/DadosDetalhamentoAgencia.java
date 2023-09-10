package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Agencia;

public record DadosDetalhamentoAgencia(Long id, String nome, DadosDetalhamentoBanco banco) {
    public DadosDetalhamentoAgencia(Agencia agencia) {
        this(agencia.getId(), agencia.getNome(), new DadosDetalhamentoBanco(agencia.getBanco()));
    }
}
