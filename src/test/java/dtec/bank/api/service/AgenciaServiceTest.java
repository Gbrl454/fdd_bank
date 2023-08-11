package dtec.bank.api.service;

import dtec.bank.api.entity.Banco;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosDetalhamentoBanco;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.utils.BankLocateResolver;
import dtec.bank.api.utils.Pais;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AgenciaServiceTest {

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

    @Test
    @DisplayName("Cadastrando Agência com dados válidos")
    void testCadastrarAgencia() {
    }

    @Test
    @DisplayName("Cadastrando Agência com ID de Banco inexistente")
    void testCadastrarAgenciaBancoInvalido() {
    }

    @Test
    @DisplayName("Cadastrando Agência com mesmo Nome que um preexistente")
    void testCadastrarBancoNomeDuplicado() {}

    @Test
    @DisplayName("Cadastrando Agências com mesmo Nome")
    void testCadastrarAgenciaComNomesIguais() {}



}
