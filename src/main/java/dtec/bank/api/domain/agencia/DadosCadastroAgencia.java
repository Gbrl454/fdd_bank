package dtec.bank.api.domain.agencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAgencia(@NotBlank(message = "{nome.obrigatorio}") String nome,
                                   @NotNull(message = "{banco.obrigatorio}") Long idBanco) {
}
