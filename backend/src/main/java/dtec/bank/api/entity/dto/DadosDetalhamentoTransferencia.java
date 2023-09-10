package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Transferencia;

import java.time.LocalDateTime;

public record DadosDetalhamentoTransferencia(
        Long id,
        Boolean sucesso,
        String motivo,
        Long valor,
        DadosDetalhamentoConta ContaOrigem,
        DadosDetalhamentoConta ContaDestino,
        DadosDetalhamentoMoeda moeda,
        LocalDateTime horarioTranferencia
) {
    public DadosDetalhamentoTransferencia(Transferencia transferencia) {
        this(
                transferencia.getId(),
                transferencia.getSucesso(),
                transferencia.getMotivo(),
                transferencia.getValor(),
                new DadosDetalhamentoConta(transferencia.getOConta()),
                new DadosDetalhamentoConta(transferencia.getDConta()),
                new DadosDetalhamentoMoeda(transferencia.getOConta().getBanco().getPais().getMoeda()),
                transferencia.getHorario_tranferencia()
        );
    }
}
