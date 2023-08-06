package dtec.bank.api.entity.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank(message = "{login.obrigatorio}") String login,
        @NotBlank(message = "{senha.obrigatorio}") String senha) {
}
