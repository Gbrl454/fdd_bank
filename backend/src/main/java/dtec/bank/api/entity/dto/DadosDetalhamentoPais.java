package dtec.bank.api.entity.dto;

import dtec.bank.api.utils.Idioma;
import dtec.bank.api.utils.Pais;

public record DadosDetalhamentoPais(String nome, Idioma idioma, int utc, DadosDetalhamentoMoeda moeda) {
    public DadosDetalhamentoPais(Pais pais) {
        this(pais.getNome(), pais.getIdioma(), pais.getUtc(), new DadosDetalhamentoMoeda(pais.getMoeda()));
    }
}
