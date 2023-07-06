package dtec.bank.api.controller;

import dtec.bank.api.domain.agencia.Agencia;
import dtec.bank.api.domain.agencia.AgenciaRepository;
import dtec.bank.api.domain.agencia.DadosCadastroAgencia;
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
@RequestMapping("/agencias")
public class AgenciaController {

    @Autowired
    private AgenciaRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid DadosCadastroAgencia dados) {
//        var agencia = new Agencia(dados);
        //        repository.save(agencia);
    }


}
