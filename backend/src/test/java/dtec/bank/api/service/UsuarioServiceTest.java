package dtec.bank.api.service;

import dtec.bank.api.ConfigTests;
import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroUsuario;
import dtec.bank.api.entity.dto.DadosDetalhamentoUsuario;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.repository.UsuarioRepository;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest extends ConfigTests {

    @Mock
    private BankLocateResolver locateResolver;
    @Mock
    private MessageSource messageSource;
    @Mock
    private HttpServletRequest request;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PasswordEncoder encoder;
    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private AgenciaRepository agenciaRepository;
    @Mock
    private BancoRepository bancoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
    }

    @Test
    @DisplayName("Cadastrando Usuário com dados válidos")
    void testCadastrarUsuario() {
        DadosCadastroUsuario dados = new DadosCadastroUsuario("UserName", "user@email.com", "userLogin", "123");
        Usuario usuario = new Usuario(dados);

        when(encoder.encode(usuario.getSenha())).thenReturn(anyString());
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(null);
        when(usuarioRepository.findByLogin(usuario.getLogin())).thenReturn(null);

        DadosDetalhamentoUsuario resultado = usuarioService.cadastrar(dados);

        assertNotNull(resultado);
        assertEquals(dados.nome(), resultado.nome());
        assertEquals(dados.email(), resultado.email());
        assertEquals(dados.login(), resultado.login());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Cadastrando Usuário com mesmo Email que um preexistente")
    void testCadastrarUsuarioEmailDuplicado() {
        DadosCadastroUsuario dados = new DadosCadastroUsuario("UserName", "user@email.com", "userLogin", "123");
        Usuario usuario = new Usuario(dados);

        when(encoder.encode(usuario.getSenha())).thenReturn(anyString());
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(null);
        when(usuarioRepository.findByLogin(usuario.getLogin())).thenReturn(new Usuario());
        when(messageSource.getMessage("usuario.login.therealready", null, locateResolver.resolveLocale(request)))
                .thenReturn(usuarioLoginTherealready);

        assertEquals(
                usuarioLoginTherealready,
                assertThrows(DataIntegrityViolationException.class, () -> usuarioService.cadastrar(dados)).getMessage());
        verify(usuarioRepository, times(0)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Cadastrando Usuário com mesmo Login que um preexistente")
    void testCadastrarUsuarioLoginDuplicado() {
        DadosCadastroUsuario dados = new DadosCadastroUsuario("UserName", "user@email.com", "userLogin", "123");
        Usuario usuario = new Usuario(dados);

        when(encoder.encode(usuario.getSenha())).thenReturn(anyString());
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(new Usuario());
        when(messageSource.getMessage("usuario.email.therealready", null, locateResolver.resolveLocale(request)))
                .thenReturn(usuarioEmailTherealready);

        assertEquals(
                usuarioEmailTherealready,
                assertThrows(DataIntegrityViolationException.class, () -> usuarioService.cadastrar(dados)).getMessage());
        verify(usuarioRepository, times(0)).save(any(Usuario.class));
    }
}
