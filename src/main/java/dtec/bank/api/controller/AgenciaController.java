package dtec.bank.api.controller;

import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.service.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agencias")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroAgencia dados) {
        var dto = agenciaService.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }


}
