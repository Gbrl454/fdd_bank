package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Banco;

public record DadosDetalhamentoBanco(Long id, String nome, DadosDetalhamentoPais pais) {
    public DadosDetalhamentoBanco(Banco banco) {
        this(banco.getId(), banco.getNome(), new DadosDetalhamentoPais(banco.getPais()));
    }
}
