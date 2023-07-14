package dtec.bank.api.service;

import dtec.bank.api.domain.usuario.DadosCadastroUsuario;
import dtec.bank.api.domain.usuario.DadosDetalhamentoUsuario;
import dtec.bank.api.domain.usuario.Usuario;
import dtec.bank.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public DadosDetalhamentoUsuario cadastrar (DadosCadastroUsuario dados) {
        var usuario = new Usuario(dados);
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new DataIntegrityViolationException("Já existe um Usuário com esse Email");
        }

        if (usuarioRepository.findByLogin(usuario.getLogin()) != null) {
            throw new DataIntegrityViolationException("Já existe um Usuário com esse Login");
        }

        usuarioRepository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }
}
