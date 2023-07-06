package dtec.bank.api.controller;

import dtec.bank.api.domain.banco.Banco;
import dtec.bank.api.domain.banco.BancoRepository;
import dtec.bank.api.domain.banco.DadosCadastroBanco;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bancos")
public class BancoController {

    @Autowired
    private BancoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid DadosCadastroBanco dados) {
        var banco = new Banco(dados);
        repository.save(banco);
    }


}
