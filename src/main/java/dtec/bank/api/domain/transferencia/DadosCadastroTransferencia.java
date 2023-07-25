package dtec.bank.api.domain.transferencia;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTransferencia(@NotNull(message = "{banco.obrigatorio}") Long idOBanco,
                                         @NotNull(message = "{agencia.obrigatorio}") Long idOAgencia,
                                         @NotNull(message = "{conta.obrigatorio}") Long idOConta,
                                         @NotNull(message = "{banco.obrigatorio}") Long idDBanco,
                                         @NotNull(message = "{agencia.obrigatorio}") Long idDAgencia,
                                         @NotNull(message = "{conta.obrigatorio}") Long idDConta,
                                         @NotNull(message = "{valor_transferencia.obrigatorio}") @DecimalMin(value = "1.00", message = "O valor mínimo é de R$ 1.00") @DecimalMax(value = "999999999.99", message = "O valor máximo é de R$ 999999999.99") Double valor) {
}
