package dtec.bank.api.controller;

import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosDetalhamentoBanco;
import dtec.bank.api.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bancos")
public class BancoController {
    private final BancoService bancoService = new BancoService();

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoBanco> cadastrar(@RequestBody @Valid DadosCadastroBanco dados) {
        var dto = bancoService.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }
}
