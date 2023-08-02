package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Transferencia;

import java.time.LocalDateTime;

public record DadosDetalhamentoTransferencia(
        Long id,
        Boolean sucesso,
        String motivo,
        Long valor,
        Long idContaOrigem,
        Long idContaDestino,
        DadosDetalhamentoMoeda moeda,
        LocalDateTime horarioTranferencia
) {
    public DadosDetalhamentoTransferencia (Transferencia transferencia) {
        this(
                transferencia.getId(),
                transferencia.getSucesso(),
                transferencia.getMotivo(),
                transferencia.getValor(),
                transferencia.getOConta().getId(),
                transferencia.getDConta().getId(),
                new DadosDetalhamentoMoeda(transferencia.getOConta().getMoeda()),
                transferencia.getHorario_tranferencia()
        );
    }
}
