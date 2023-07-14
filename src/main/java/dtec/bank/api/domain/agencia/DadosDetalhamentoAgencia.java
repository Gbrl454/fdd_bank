package dtec.bank.api.domain.agencia;

public record DadosDetalhamentoAgencia(Long id, String nome, Long idbanco) {
    public DadosDetalhamentoAgencia (Agencia agencia) {
        this(agencia.getId(), agencia.getNome(), agencia.getBanco().getId());
    }
}
