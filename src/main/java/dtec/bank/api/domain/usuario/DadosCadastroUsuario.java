package dtec.bank.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank(message = "{nome.obrigatorio}") String nome,
                                   @Email(message = "{email.invalido}") @NotBlank(message = "{email.obrigatorio}") String email,
                                   @NotBlank(message = "{login.obrigatorio}") String login,
                                   @NotBlank(message = "{senha.obrigatorio}") String senha) {
}
