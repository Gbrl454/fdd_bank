package dtec.bank.api.controller;

import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
import dtec.bank.api.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
    @Autowired
    private TransferenciaService transferenciaService;


    @PostMapping
    public ResponseEntity<DadosDetalhamentoTransferencia> transferir(@RequestBody @Valid DadosCadastroTransferencia dados, @AuthenticationPrincipal Usuario logado) {
        DadosDetalhamentoTransferencia dto = transferenciaService.register(dados, logado);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoTransferencia>> listAll(@AuthenticationPrincipal Usuario logado) {
        List<DadosDetalhamentoTransferencia> dto = transferenciaService.listAll(logado);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{idTransferencia}")
    public ResponseEntity<DadosDetalhamentoTransferencia> detailtransferencia(@AuthenticationPrincipal Usuario logado,@PathVariable Long idTransferencia) {
        DadosDetalhamentoTransferencia dto = transferenciaService.transferenciaById(idTransferencia,logado);
        return ResponseEntity.ok(dto);
    }
}
