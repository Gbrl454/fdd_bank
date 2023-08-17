package dtec.bank.api.controller;

import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaService contaService = new ContaService();

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConta> cadastrar(@RequestBody @Valid DadosCadastroConta dados) {
        var dto = contaService.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }
}
