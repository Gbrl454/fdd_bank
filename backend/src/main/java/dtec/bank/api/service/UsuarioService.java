package dtec.bank.api.service;

import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroUsuario;
import dtec.bank.api.entity.dto.DadosDetalhamentoCompletoUsuario;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoUsuario;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.UsuarioRepository;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    BankLocateResolver locateResolver;
    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    PasswordEncoder encoder;


    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dados) {
        var usuario = new Usuario(dados);

        usuario.setSenha(encoder.encode(usuario.getSenha()));

        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new DataIntegrityViolationException(get("usuario.email.therealready"));
        }

        if (usuarioRepository.findByLogin(usuario.getLogin()) != null) {
            throw new DataIntegrityViolationException(get("usuario.login.therealready"));
        }

        usuarioRepository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    public DadosDetalhamentoCompletoUsuario getUser(Usuario logado) {
        Usuario usuario = usuarioRepository.getReferenceById(logado.getId());

        List<DadosDetalhamentoConta> list = contaRepository.findAllByIdUsuario(logado.getId());

        if (list.isEmpty()) throw new ValidacaoException(get("conta.list.empty.byidusuario"));

        return new DadosDetalhamentoCompletoUsuario(usuario, list);
    }
}
