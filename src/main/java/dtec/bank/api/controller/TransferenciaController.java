package dtec.bank.api.controller;

import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
import dtec.bank.api.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
    private final TransferenciaService transferenciaService = new TransferenciaService();

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTransferencia> transferir(@RequestBody @Valid DadosCadastroTransferencia dados) {
        var dto = transferenciaService.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }
}
