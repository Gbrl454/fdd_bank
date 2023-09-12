package dtec.bank.api.controller;

import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroUsuario;
import dtec.bank.api.entity.dto.DadosDetalhamentoCompletoUsuario;
import dtec.bank.api.entity.dto.DadosDetalhamentoUsuario;
import dtec.bank.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        DadosDetalhamentoUsuario dto = usuarioService.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<DadosDetalhamentoCompletoUsuario> get(@AuthenticationPrincipal Usuario logado) {
        DadosDetalhamentoCompletoUsuario dto = usuarioService.getUser(logado);
        return ResponseEntity.ok(dto);
    }
}
