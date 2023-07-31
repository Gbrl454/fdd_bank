package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Banco;

public record DadosDetalhamentoBanco(Long id, String nome) {
    public DadosDetalhamentoBanco (Banco banco) {
        this(banco.getId(), banco.getNome());
    }
}
