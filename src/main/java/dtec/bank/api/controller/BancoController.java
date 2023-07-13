package dtec.bank.api.controller;

import dtec.bank.api.domain.banco.Banco;
import dtec.bank.api.domain.banco.BancoRepository;
import dtec.bank.api.domain.banco.DadosCadastroBanco;
import dtec.bank.api.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bancos")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroBanco dados) {
        var dto = bancoService.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }


}
