package dtec.bank.api.service;

import dtec.bank.api.entity.Banco;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosDetalhamentoBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BancoService {
    @Autowired
    BancoRepository bancoRepository;

    public DadosDetalhamentoBanco cadastrar (DadosCadastroBanco dados) {
        var banco = new Banco(dados);
        if (bancoRepository.findByNome(banco.getNome()) != null) {
            throw new DataIntegrityViolationException("Já existe um banco com esse nome");
        }

        bancoRepository.save(banco);
        return new DadosDetalhamentoBanco(banco);
    }
}
