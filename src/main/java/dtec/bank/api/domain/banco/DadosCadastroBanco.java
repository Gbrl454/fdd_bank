package dtec.bank.api.domain.banco;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroBanco(@NotBlank(message = "{nome.obrigatorio}") String nome) {
}
