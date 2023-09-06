package dtec.bank.api.entity.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTransferencia(Long idOConta,
                                         @NotNull(message = "{conta.obrigatorio}") Long idDConta,
                                         @NotNull(message = "{valor_transferencia.obrigatorio}") @DecimalMin(value = "1.00", message = "O valor mínimo é de R$ 1.00") @DecimalMax(value = "999999999.99", message = "O valor máximo é de R$ 999999999.99") Double valor) {
}
