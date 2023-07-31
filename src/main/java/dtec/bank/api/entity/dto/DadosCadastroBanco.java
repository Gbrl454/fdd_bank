package dtec.bank.api.entity.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroBanco(@NotBlank(message = "{nome.obrigatorio}") String nome) {
}
