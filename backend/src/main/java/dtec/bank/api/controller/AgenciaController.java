package dtec.bank.api.controller;

import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoAgencia;
import dtec.bank.api.service.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agencias")
public class AgenciaController {
    @Autowired
    private AgenciaService agenciaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAgencia> cadastrar(@RequestBody @Valid DadosCadastroAgencia dados) {
        DadosDetalhamentoAgencia dto = agenciaService.register(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoAgencia>> listAll() {
        List<DadosDetalhamentoAgencia> dto = agenciaService.listAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{idBanco}")
    public ResponseEntity<List<DadosDetalhamentoAgencia>> listAllByIdBanco(@PathVariable Long idBanco) {
        List<DadosDetalhamentoAgencia> dto = agenciaService.listAllByIdBanco(idBanco);
        return ResponseEntity.ok(dto);
    }

}
