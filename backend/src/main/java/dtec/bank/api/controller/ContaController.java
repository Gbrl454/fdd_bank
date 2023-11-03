package dtec.bank.api.controller;

import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConta> register(@RequestBody @Valid DadosCadastroConta dados) {
        DadosDetalhamentoConta dto = contaService.register(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoConta>> listAll(@AuthenticationPrincipal Usuario logado) {
        List<DadosDetalhamentoConta> dto = contaService.listAll(logado);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{idConta}")
    public ResponseEntity<DadosDetalhamentoConta> detailBanco(@PathVariable Long idConta) {
        DadosDetalhamentoConta dto = contaService.contaById(idConta);
        return ResponseEntity.ok(dto);
    }
}
