package dtec.bank.api.entity.dto;

import java.time.LocalDateTime;

public record DadosListagemTransferencia(
        Long id,
        Boolean sucesso,
        String motivo,
        Double valor,
        String nomeUserContaOrigem,
        String nomeUserContaDestino,
        DadosDetalhamentoMoeda moeda,
        LocalDateTime horarioTranferencia
) {
}
