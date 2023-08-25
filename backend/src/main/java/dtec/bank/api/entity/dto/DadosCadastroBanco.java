package dtec.bank.api.entity.dto;

import dtec.bank.api.utils.Pais;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroBanco(@NotBlank(message = "{nome.obrigatorio}") String nome,
                                 @NotNull(message = "{pais.obrigatorio}") Pais pais) {
}
