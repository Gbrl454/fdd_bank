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
    private BankLocateResolver locateResolver;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BancoRepository bancoRepository;

    public DadosDetalhamentoBanco cadastrar(DadosCadastroBanco dados) {
        var banco = new Banco(dados);
        if (bancoRepository.findByNome(banco.getNome()) != null) {
            throw new DataIntegrityViolationException(messageSource.getMessage("banco.nome.therealready", null, locateResolver.resolveLocale(request)));
        }

        bancoRepository.save(banco);
        return new DadosDetalhamentoBanco(banco);
    }
}
