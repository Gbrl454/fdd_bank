package dtec.bank.api.service;

import dtec.bank.api.entity.Agencia;
import dtec.bank.api.entity.Banco;
import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoAgencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoBanco;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public DadosDetalhamentoAgencia register(DadosCadastroAgencia dados) {
        if (!bancoRepository.existsById(dados.idBanco())) throw new ValidacaoException(get("banco.id.notexist"));
        if (agenciaRepository.findByNome(dados.nome()) != null)
            throw new DataIntegrityViolationException(get("agencia.nome.therealready"));

        Banco banco = bancoRepository.getReferenceById(dados.idBanco());
        Agencia agencia = new Agencia(null, dados.nome(), banco);

        agenciaRepository.save(agencia);
        return new DadosDetalhamentoAgencia(agencia);
    }

    public List<DadosDetalhamentoAgencia> listAll() {
        List<DadosDetalhamentoAgencia> list = agenciaRepository.findAll().stream().map(DadosDetalhamentoAgencia::new).toList();

        if (list.isEmpty()) throw new ValidacaoException(get("agencia.list.empty.all"));

        return list;
    }

    public List<DadosDetalhamentoAgencia> listAllByIdBanco(Long idBanco) {
        if (!bancoRepository.existsById(idBanco)) throw new ValidacaoException(get("banco.id.notexist"));

        List<DadosDetalhamentoAgencia> list = agenciaRepository.findAllByIdBanco(idBanco);

        if (list.isEmpty()) throw new ValidacaoException(get("agencia.list.empty.byidbanco"));

        return list;
    }
}
