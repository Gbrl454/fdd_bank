package dtec.bank.api.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String email, String login) {
    public DadosDetalhamentoUsuario (Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getLogin());
    }
}
