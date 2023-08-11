package dtec.bank.api.service;

import dtec.bank.api.entity.Banco;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosDetalhamentoBanco;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.utils.BankLocateResolver;
import dtec.bank.api.utils.Pais;
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
import static org.mockito.Mockito.when;

class BancoServiceTest {

    @InjectMocks
    private BancoService bancoService;
    @Mock
    private BankLocateResolver locateResolver;
    @Mock
    private MessageSource messageSource;
    @Mock
    private BancoRepository bancoRepository;
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
    }

    @Test
    @DisplayName("Cadastrando Banco com dados válidos")
    void testCadastrarBanco() {
        DadosCadastroBanco dados = new DadosCadastroBanco("Banco", Pais.BRA);
        when(bancoRepository.findByNome(dados.nome())).thenReturn(null);

        DadosDetalhamentoBanco resultado = bancoService.cadastrar(dados);

        assertNotNull(resultado);
        assertEquals(dados.nome(), resultado.nome());
    }

    @Test
    @DisplayName("Cadastrando Banco com mesmo Nome que um preexistente")
    void testCadastrarBancoNomeDuplicado() {
        DadosCadastroBanco dados = new DadosCadastroBanco("Banco", Pais.BRA);
        when(bancoRepository.findByNome(dados.nome())).thenReturn(new Banco(dados));
        when(messageSource.getMessage("banco.nome.therealready", null, locateResolver.resolveLocale(request))).thenReturn("Já existe um Banco com esse Nome");

        assertEquals(
                assertThrows(DataIntegrityViolationException.class, () -> bancoService.cadastrar(dados)).getMessage(),
                "Já existe um Banco com esse Nome");
    }

    @Test
    @DisplayName("Cadastrando Bancos com mesmo Nome")
    void testCadastrarBancoComNomesIguais() {
        DadosCadastroBanco dados1 = new DadosCadastroBanco("Banco", Pais.BRA);
        DadosCadastroBanco dados2 = new DadosCadastroBanco("Banco", Pais.USA);
        when(bancoRepository.findByNome(dados1.nome())).thenReturn(null);

        DadosDetalhamentoBanco resultado1 = bancoService.cadastrar(dados1);

        assertNotNull(resultado1);
        assertEquals(dados1.nome(), resultado1.nome());

        when(bancoRepository.findByNome(dados2.nome())).thenReturn(new Banco(dados1));

        assertEquals(
                assertThrows(DataIntegrityViolationException.class, () -> bancoService.cadastrar(dados2)).getMessage(),
                "Já existe um Banco com esse Nome");
    }
}
