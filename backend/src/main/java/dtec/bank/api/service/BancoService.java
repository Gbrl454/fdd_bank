package dtec.bank.api.service;

import dtec.bank.api.entity.Banco;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosDetalhamentoBanco;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    public DadosDetalhamentoBanco register(DadosCadastroBanco dados) {
        if (bancoRepository.findByNome(dados.nome()) != null)
            throw new DataIntegrityViolationException(get("banco.nome.therealready"));

        var banco = new Banco(dados);

        bancoRepository.save(banco);
        return new DadosDetalhamentoBanco(banco);
    }

    public List<DadosDetalhamentoBanco> listAll() {
        List<DadosDetalhamentoBanco> list = bancoRepository.findAll().stream().map(DadosDetalhamentoBanco::new).toList();

        if (list.isEmpty())
            throw new ValidacaoException(get("banco.list.empty"));

        return list;
    }

    public DadosDetalhamentoBanco bancoById(Long idBanco) {
        if (!bancoRepository.existsById(idBanco))
            throw new ValidacaoException(get("banco.id.notexist"));

        return new DadosDetalhamentoBanco(bancoRepository.getReferenceById(idBanco));
    }
}
