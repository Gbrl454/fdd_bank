package dtec.bank.api.entity.dto;

import dtec.bank.api.utils.Pais;

public record DadosDetalhamentoPais(String nome, String idioma, int utc) {
    public DadosDetalhamentoPais (Pais pais) {
        this(pais.getNome(), pais.getIdioma(), pais.getUtc());
    }
}
