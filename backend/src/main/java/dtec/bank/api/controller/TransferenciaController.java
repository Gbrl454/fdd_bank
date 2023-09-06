package dtec.bank.api.controller;

import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosListagemTransferencia;
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
    public ResponseEntity transferir(@RequestBody @Valid DadosCadastroTransferencia dados, @AuthenticationPrincipal Usuario logado) {
        var dto = transferenciaService.cadastrar(dados, logado);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemTransferencia>> listar(@AuthenticationPrincipal Usuario logado) {
        var dto = transferenciaService.listar(logado);
        return ResponseEntity.ok(dto);
    }
}
