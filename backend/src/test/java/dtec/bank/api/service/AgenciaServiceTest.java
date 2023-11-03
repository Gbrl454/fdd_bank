package dtec.bank.api.service;

import dtec.bank.api.ConfigTests;
import dtec.bank.api.entity.Agencia;
import dtec.bank.api.entity.Banco;
import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoAgencia;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgenciaServiceTest extends ConfigTests {

    @InjectMocks
    private AgenciaService agenciaService;
    @Mock
    private BankLocateResolver locateResolver;
    @Mock
    private MessageSource messageSource;
    @Mock
    private HttpServletRequest request;
    @Mock
    private AgenciaRepository agenciaRepository;
    @Mock
    private BancoRepository bancoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
    }

//    @Test
//    @DisplayName("Cadastrando Agência com dados válidos")
//    void testCadastrarAgencia() {
//        DadosCadastroAgencia dados = getDadosCadastroAgencia();
//
//        when(bancoRepository.existsById(dados.idBanco())).thenReturn(true);
//        when(bancoRepository.getReferenceById(dados.idBanco())).thenReturn(new Banco());
//        when(agenciaRepository.findByNome(dados.nome())).thenReturn(null);
//
//        DadosDetalhamentoAgencia resultado = agenciaService.register(dados);
//
//        assertNotNull(resultado);
//        assertEquals(dados.nome(), resultado.nome());
//        verify(agenciaRepository, times(1)).save(any(Agencia.class));
//    }
//
//    @Test
//    @DisplayName("Cadastrando Agência com ID de Banco inexistente")
//    void testCadastrarAgenciaBancoInvalido() {
//        DadosCadastroAgencia dados = getDadosCadastroAgencia();
//
//        when(bancoRepository.existsById(dados.idBanco())).thenReturn(false);
//        when(bancoRepository.getReferenceById(dados.idBanco())).thenReturn(null);
//        when(messageSource.getMessage("banco.id.notexist", null, locateResolver.resolveLocale(request)))
//                .thenReturn(bancoIdNotexist);
//
//        assertEquals(
//                bancoIdNotexist,
//                assertThrows(ValidacaoException.class, () -> agenciaService.register(dados)).getMessage());
//        verify(agenciaRepository, times(0)).save(any(Agencia.class));
//    }
//
//    @Test
//    @DisplayName("Cadastrando Agência com mesmo Nome que uma preexistente")
//    void testCadastrarAgenciaNomeDuplicado() {
//        DadosCadastroAgencia dados = getDadosCadastroAgencia();
//
//        when(bancoRepository.existsById(dados.idBanco())).thenReturn(true);
//        when(bancoRepository.getReferenceById(dados.idBanco())).thenReturn(new Banco());
//        when(agenciaRepository.findByNome(dados.nome())).thenReturn(new Agencia());
//        when(messageSource.getMessage("agencia.nome.therealready", null, locateResolver.resolveLocale(request)))
//                .thenReturn(agenciaNomeTherealready);
//
//        assertEquals(
//                agenciaNomeTherealready,
//                assertThrows(DataIntegrityViolationException.class, () -> agenciaService.register(dados)).getMessage());
//        verify(agenciaRepository, times(0)).save(any(Agencia.class));
//    }
}
