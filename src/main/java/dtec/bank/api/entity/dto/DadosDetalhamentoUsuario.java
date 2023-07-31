package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String email, String login) {
    public DadosDetalhamentoUsuario (Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getLogin());
    }
}
