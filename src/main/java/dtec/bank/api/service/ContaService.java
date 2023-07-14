package dtec.bank.api.service;

import dtec.bank.api.domain.conta.ContaRepository;
import dtec.bank.api.domain.conta.DadosCadastroConta;
import dtec.bank.api.domain.conta.DadosDetalhamentoConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Autowired
    ContaRepository contaRepository;

    public DadosDetalhamentoConta cadastrar (DadosCadastroConta dados) {
        return null;
    }
}
