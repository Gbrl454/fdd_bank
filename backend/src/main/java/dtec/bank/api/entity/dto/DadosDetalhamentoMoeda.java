package dtec.bank.api.entity.dto;

import dtec.bank.api.utils.Moeda;

public record DadosDetalhamentoMoeda(String nome, String simbolo, float multiplicador) {
    public DadosDetalhamentoMoeda (Moeda moeda) {
        this(moeda.getNome(), moeda.getSimbolo(), moeda.getMultiplicador());
    }
}
