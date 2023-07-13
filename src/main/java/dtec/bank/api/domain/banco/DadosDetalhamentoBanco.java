package dtec.bank.api.domain.banco;

public record DadosDetalhamentoBanco(Long id, String nome) {
    public DadosDetalhamentoBanco (Banco banco) {
        this(banco.getId(), banco.getNome());
    }
}
