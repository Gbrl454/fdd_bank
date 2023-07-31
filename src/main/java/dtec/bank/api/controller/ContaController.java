package dtec.bank.api.controller;

import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroConta dados) {
        var dto = contaService.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }
}
