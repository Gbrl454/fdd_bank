package dtec.bank.api.service;

import dtec.bank.api.entity.Banco;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosDetalhamentoBanco;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BancoService {

    @Autowired
    BankLocateResolver locateResolver;
    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Autowired
    BancoRepository bancoRepository;

    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public DadosDetalhamentoBanco cadastrar(DadosCadastroBanco dados) {
        var banco = new Banco(dados);
        if (bancoRepository.findByNome(banco.getNome()) != null) {
            throw new DataIntegrityViolationException(get("banco.nome.therealready"));
        }

        bancoRepository.save(banco);
        return new DadosDetalhamentoBanco(banco);
    }
}
