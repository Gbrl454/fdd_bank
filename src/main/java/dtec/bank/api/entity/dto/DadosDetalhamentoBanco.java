package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Banco;
import dtec.bank.api.utils.Pais;

public record DadosDetalhamentoBanco(Long id, String nome, Pais pais) {
    public DadosDetalhamentoBanco (Banco banco) {
        this(banco.getId(), banco.getNome(), banco.getPais());
    }
}
