package dtec.bank.api.entity.dto;

import jakarta.validation.constraints.NotNull;

public record DadosListTransferencia(
        @NotNull(message = "utc.inform") int utc
) {

}
