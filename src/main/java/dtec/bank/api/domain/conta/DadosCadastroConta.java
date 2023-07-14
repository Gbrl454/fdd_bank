package dtec.bank.api.domain.conta;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroConta(@NotNull(message = "{agencia.obrigatorio}") Long idAgencia,
                                 @NotNull(message = "{usuario.obrigatorio}") Long idUsuario,
                                 @NotNull(message = "{saldo.obrigatorio}") @DecimalMin(value = "0.00", inclusive = true, message = "O valor mínimo é de R$ 00.00") @DecimalMax(value = "999999999.99", inclusive = true, message = "O valor máximo é de R$ 999999999.99") BigDecimal saldo,
                                 @NotNull(message = "{tipo.obrigatorio}") TipoConta tipo, Boolean cartao_de_credito,
                                 @DecimalMin(value = "0.00", inclusive = true, message = "O valor mínimo é de R$ 00.00") @DecimalMax(value = "999999999.99", inclusive = true, message = "O valor máximo é de R$ 999999999.99") BigDecimal saldo_cartao_de_credito,
                                 Boolean lis,
                                 @DecimalMin(value = "0.00", inclusive = true, message = "O valor mínimo é de R$ 00.00") @DecimalMax(value = "999999999.99", inclusive = true, message = "O valor máximo é de R$ 999999999.99") BigDecimal saldo_lis) {
}
