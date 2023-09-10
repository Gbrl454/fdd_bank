package dtec.bank.api.controller;

import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosDetalhamentoBanco;
import dtec.bank.api.service.BancoService;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bancos")
public class BancoController {
    @Autowired
    private BancoService bancoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoBanco> register(@RequestBody @Valid DadosCadastroBanco dados) {
        DadosDetalhamentoBanco dto = bancoService.register(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoBanco>> listAll() {
        List<DadosDetalhamentoBanco> dto = bancoService.listAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{idBanco}")
    public ResponseEntity<DadosDetalhamentoBanco> detailBanco(@PathVariable Long idBanco) {
        DadosDetalhamentoBanco dto = bancoService.bancoById(idBanco);
        return ResponseEntity.ok(dto);
    }
}
