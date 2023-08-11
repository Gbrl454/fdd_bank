package dtec.bank.api.service;

import dtec.bank.api.entity.Agencia;
import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoAgencia;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AgenciaService {

    @Autowired
    BankLocateResolver locateResolver;
    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Autowired
    AgenciaRepository agenciaRepository;
    @Autowired
    BancoRepository bancoRepository;

    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public DadosDetalhamentoAgencia cadastrar(DadosCadastroAgencia dados) {
        if (dados.idBanco() != null && !bancoRepository.existsById(dados.idBanco())) {
            throw new ValidacaoException(get("banco.id.notexist"));
        }

        var banco = bancoRepository.getReferenceById(dados.idBanco());

        var agencia = new Agencia(null, dados.nome(), banco);

        if (agenciaRepository.findByNome(agencia.getNome()) != null) {
            throw new DataIntegrityViolationException(get("agencia.nome.therealready"));
        }

        agenciaRepository.save(agencia);
        return new DadosDetalhamentoAgencia(agencia);
    }
}
