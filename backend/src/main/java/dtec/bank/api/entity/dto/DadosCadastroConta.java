package dtec.bank.api.entity.dto;

import dtec.bank.api.utils.TipoConta;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroConta(
        @NotNull(message = "{tipo.obrigatorio}") TipoConta tipo,
        @DecimalMin(value = "0.00", message = "O valor mínimo é de R$ 00.00") @DecimalMax(value = "999999999.99", message = "O valor máximo é de R$ 999999999.99") Double saldo,
        Boolean cartao_de_credito,
        @DecimalMin(value = "0.00", message = "O valor mínimo é de R$ 00.00") @DecimalMax(value = "999999999.99", message = "O valor máximo é de R$ 999999999.99") Double saldo_cartao_de_credito,
        Boolean lis,
        @DecimalMin(value = "0.00", message = "O valor mínimo é de R$ 00.00") @DecimalMax(value = "999999999.99", message = "O valor máximo é de R$ 999999999.99") Double saldo_lis,
        @NotNull(message = "{agencia.obrigatorio}") Long idAgencia,
        Long idUsuario) {
}
