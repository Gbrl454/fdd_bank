package dtec.bank.api.service;

import dtec.bank.api.ConfigTests;
import dtec.bank.api.entity.Agencia;
import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.UsuarioRepository;
import dtec.bank.api.utils.BankLocateResolver;
import dtec.bank.api.utils.Moeda;
import dtec.bank.api.utils.TipoConta;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContaServiceTest extends ConfigTests {
    @InjectMocks
    private ContaService contaService;
    @Mock
    private BankLocateResolver locateResolver;
    @Mock
    private MessageSource messageSource;
    @Mock
    private HttpServletRequest request;
    @Mock
    private AgenciaRepository agenciaRepository;
    @Mock
    private ContaRepository contaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo NORMAL com dados válidos")
    void testCadastrarContaNormal() {
        DadosCadastroConta dados = getContaNormal();

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());

        DadosDetalhamentoConta resultado = contaService.cadastrar(dados);

        assertNotNull(resultado);
        assertEquals(dados.moeda().getSimbolo(),
                resultado.moeda().simbolo());
        assertEquals((long) (dados.saldo() * dados.moeda().getMultiplicador()),
                resultado.saldo());
        assertEquals(dados.tipo(), resultado.tipo());
        assertEquals(dados.cartao_de_credito(), resultado.cartao_de_credito());
        assertEquals((long) (dados.saldo_cartao_de_credito() * dados.moeda().getMultiplicador()),
                resultado.saldo_cartao_de_credito());
        assertEquals(dados.lis(), resultado.lis());
        assertEquals((long) (dados.saldo_lis() * dados.moeda().getMultiplicador()),
                resultado.saldo_lis());
        verify(contaRepository, times(1)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo ESPECIAL com dados válidos")
    void testCadastrarContaEspecial() {
        DadosCadastroConta dados = getContaEspecial();

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());

        DadosDetalhamentoConta resultado = contaService.cadastrar(dados);

        assertNotNull(resultado);
        assertEquals(dados.moeda().getSimbolo(),
                resultado.moeda().simbolo());
        assertEquals((long) (dados.saldo() * dados.moeda().getMultiplicador()),
                resultado.saldo());
        assertEquals(dados.tipo(), resultado.tipo());
        assertEquals(dados.cartao_de_credito(), resultado.cartao_de_credito());
        assertEquals((long) (dados.saldo_cartao_de_credito() * dados.moeda().getMultiplicador()),
                resultado.saldo_cartao_de_credito());
        assertEquals(dados.lis(), resultado.lis());
        assertEquals((long) (dados.saldo_lis() * dados.moeda().getMultiplicador()),
                resultado.saldo_lis());
        verify(contaRepository, times(1)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo PREMIUM com dados válidos")
    void testCadastrarContaPremium() {
        DadosCadastroConta dados = getContaPremium();

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());

        DadosDetalhamentoConta resultado = contaService.cadastrar(dados);

        assertNotNull(resultado);
        assertEquals(dados.moeda().getSimbolo(),
                resultado.moeda().simbolo());
        assertEquals((long) (dados.saldo() * dados.moeda().getMultiplicador()),
                resultado.saldo());
        assertEquals(dados.tipo(), resultado.tipo());
        assertEquals(dados.cartao_de_credito(), resultado.cartao_de_credito());
        assertEquals((long) (dados.saldo_cartao_de_credito() * dados.moeda().getMultiplicador()),
                resultado.saldo_cartao_de_credito());
        assertEquals(dados.lis(), resultado.lis());
        assertEquals((long) (dados.saldo_lis() * dados.moeda().getMultiplicador()),
                resultado.saldo_lis());
        verify(contaRepository, times(1)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta com ID de Agência inexistente")
    void testCadastrarAgenciaInvalida() {
        DadosCadastroConta dados = getContaNormal();

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(false);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(null);
        when(messageSource.getMessage("agencia.id.notexist", null, locateResolver.resolveLocale(request)))
                .thenReturn(agenciaIdNotexist);

        assertEquals(
                agenciaIdNotexist,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta com ID de Usuário inexistente")
    void testCadastrarUsuarioInvalido() {
        DadosCadastroConta dados = getContaNormal();

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(false);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(null);
        when(messageSource.getMessage("usuario.id.notexist", null, locateResolver.resolveLocale(request)))
                .thenReturn(usuarioIdNotexist);

        assertEquals(
                usuarioIdNotexist,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo NORMAL informando possuir Cartão de Credito")
    void testCadastrarContaNormalInformadoCartaoDeCredito() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.NORMAL,
                true, 0.0, false, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("conta.normal.notown.creditcard", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaNormalNotownCreditcard);

        assertEquals(TipoConta.NORMAL, dados.tipo());
        assertNotNull(dados.cartao_de_credito());
        assertTrue(dados.cartao_de_credito());

        assertEquals(
                contaNormalNotownCreditcard,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo NORMAL informando Saldo do Cartão de Credito")
    void testCadastrarContaNormalInformadoSaldoDoCartaoDeCredito() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.NORMAL,
                false, 500.0, false, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("conta.normal.notown.creditcard", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaNormalNotownCreditcard);

        assertEquals(TipoConta.NORMAL, dados.tipo());
        assertNotNull(dados.saldo_cartao_de_credito());
        assertTrue(dados.saldo_cartao_de_credito() > 0);

        assertEquals(
                contaNormalNotownCreditcard,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo NORMAL informando possuir Cheque Especial")
    void testCadastrarContaNormalInformadoChequeEspecial() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.NORMAL,
                false, 0.0, true, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("conta.normal.notown.lis", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaNormalNotownLis);

        assertEquals(TipoConta.NORMAL, dados.tipo());
        assertNotNull(dados.lis());
        assertTrue(dados.lis());

        assertEquals(
                contaNormalNotownLis,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo NORMAL informando Saldo do Cheque Especial")
    void testCadastrarContaNormalInformadoSaldoDoChequeEspecial() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.NORMAL,
                false, 0.0, false, 500.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("conta.normal.notown.lis", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaNormalNotownLis);

        assertEquals(TipoConta.NORMAL, dados.tipo());
        assertNotNull(dados.lis());
        assertTrue(dados.saldo_lis() > 0);

        assertEquals(
                contaNormalNotownLis,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo ESPECIAL informando possuir Cartão de Credito, mas não informando seu Saldo")
    void testCadastrarContaEspecialInformadoPossuirCartaoDeCretidoSemInformarSaldo() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.ESPECIAL,
                true, 0.0, false, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.creditcard.informwhenhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoCreditcardInformonlyhave);

        assertEquals(TipoConta.ESPECIAL, dados.tipo());
        assertNotNull(dados.cartao_de_credito());
        assertTrue(dados.cartao_de_credito());
        assertFalse(dados.saldo_cartao_de_credito() > 0);

        assertEquals(
                saldoCreditcardInformonlyhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo ESPECIAL informando não possuir Cartão de Credito, mas informando seu Saldo")
    void testCadastrarContaEspecialInformadoSaldoCartaoDeCretidoInformandoNaoPossuirCartaoDeCretido() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.ESPECIAL,
                false, 500.0, false, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.creditcard.informonlyhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoCreditcardInformonlyhave);

        assertEquals(TipoConta.ESPECIAL, dados.tipo());
        assertNotNull(dados.cartao_de_credito());
        assertFalse(dados.cartao_de_credito());
        assertTrue(dados.saldo_cartao_de_credito() > 0);

        assertEquals(
                saldoCreditcardInformonlyhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo ESPECIAL sem possuir Cartão de Credito, mas informando seu Saldo")
    void testCadastrarContaEspecialInformadoSaldoCartaoDeCretidoSemPossuirCartaoDeCretido() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.ESPECIAL,
                null, 500.0, false, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.creditcard.informonlyhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoCreditcardInformonlyhave);

        assertEquals(TipoConta.ESPECIAL, dados.tipo());
        assertNull(dados.cartao_de_credito());
        assertTrue(dados.saldo_cartao_de_credito() > 0);

        assertEquals(
                saldoCreditcardInformonlyhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo ESPECIAL informando possuir Cheque Especial")
    void testCadastrarContaEspecialInformadoChequeEspecial() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.ESPECIAL,
                false, 0.0, true, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("conta.especial.notown.lis", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaEspecialNotownLis);

        assertEquals(TipoConta.ESPECIAL, dados.tipo());
        assertNotNull(dados.lis());
        assertTrue(dados.lis());

        assertEquals(
                contaEspecialNotownLis,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo ESPECIAL informando Saldo do Cheque Especial")
    void testCadastrarContaEspecialInformadoSaldoDoChequeEspecial() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.ESPECIAL,
                false, 0.0, false, 500.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("conta.especial.notown.lis", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaEspecialNotownLis);

        assertEquals(TipoConta.ESPECIAL, dados.tipo());
        assertNotNull(dados.lis());
        assertTrue(dados.saldo_lis() > 0);

        assertEquals(
                contaEspecialNotownLis,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo PREMIUM informando possuir Cartão de Credito, mas não informando seu Saldo")
    void testCadastrarContaPremiumInformadoPossuirCartaoDeCretidoSemInformarSaldo() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.PREMIUM,
                true, 0.0, false, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.creditcard.informwhenhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoCreditcardInformonlyhave);

        assertEquals(TipoConta.PREMIUM, dados.tipo());
        assertNotNull(dados.cartao_de_credito());
        assertTrue(dados.cartao_de_credito());
        assertFalse(dados.saldo_cartao_de_credito() > 0);

        assertEquals(
                saldoCreditcardInformonlyhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo PREMIUM informando não possuir Cartão de Credito, mas informando seu Saldo")
    void testCadastrarContaPremiumInformadoSaldoCartaoDeCretidoInformandoNaoPossuirCartaoDeCretido() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.PREMIUM,
                false, 500.0, false, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.creditcard.informonlyhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoCreditcardInformonlyhave);

        assertEquals(TipoConta.PREMIUM, dados.tipo());
        assertNotNull(dados.cartao_de_credito());
        assertFalse(dados.cartao_de_credito());
        assertTrue(dados.saldo_cartao_de_credito() > 0);

        assertEquals(
                saldoCreditcardInformonlyhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo PREMIUM sem possuir Cartão de Credito, mas informando seu Saldo")
    void testCadastrarContaPremiumInformadoSaldoCartaoDeCretidoSemPossuirCartaoDeCretido() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.PREMIUM,
                null, 500.0, false, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.creditcard.informonlyhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoCreditcardInformonlyhave);

        assertEquals(TipoConta.PREMIUM, dados.tipo());
        assertNull(dados.cartao_de_credito());
        assertTrue(dados.saldo_cartao_de_credito() > 0);

        assertEquals(
                saldoCreditcardInformonlyhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo PREMIUM informando possuir Cheque Especial, mas não informando seu Saldo")
    void testCadastrarContaPremiumInformadoPossuirChequeEspecialSemInformarSaldo() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.PREMIUM,
                false, 0.0, true, 0.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.lis.informwhenhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoLisInformwhenhave);

        assertEquals(TipoConta.PREMIUM, dados.tipo());
        assertNotNull(dados.lis());
        assertTrue(dados.lis());
        assertFalse(dados.saldo_lis() > 0);

        assertEquals(
                saldoLisInformwhenhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo PREMIUM informando não possuir Cheque Especial, mas informando seu Saldo")
    void testCadastrarContaPremiumInformadoSaldoChequeEspecialInformandoNaoPossuirChequeEspecial() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.PREMIUM,
                false, 0.0, null, 500.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.lis.informonlyhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoLisInformonlyhave);

        assertEquals(TipoConta.PREMIUM, dados.tipo());
        assertNull(dados.lis());
        assertTrue(dados.saldo_lis() > 0);

        assertEquals(
                saldoLisInformonlyhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Cadastrando Conta do tipo PREMIUM sem possuir Cheque Especial, mas informando seu Saldo")
    void testCadastrarContaPremiumInformadoSaldoChequeEspecialSemPossuirChequeEspecial() {
        DadosCadastroConta dados = new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.PREMIUM,
                false, 0.0, false, 500.0);

        when(agenciaRepository.existsById(dados.idAgencia())).thenReturn(true);
        when(agenciaRepository.getReferenceById(dados.idAgencia())).thenReturn(new Agencia());
        when(usuarioRepository.existsById(dados.idUsuario())).thenReturn(true);
        when(usuarioRepository.getReferenceById(dados.idUsuario())).thenReturn(new Usuario());
        when(messageSource.getMessage("saldo.lis.informonlyhave", null, locateResolver.resolveLocale(request)))
                .thenReturn(saldoLisInformonlyhave);

        assertEquals(TipoConta.PREMIUM, dados.tipo());
        assertNotNull(dados.lis());
        assertFalse(dados.lis());
        assertTrue(dados.saldo_lis() > 0);

        assertEquals(
                saldoLisInformonlyhave,
                assertThrows(ValidacaoException.class, () -> contaService.cadastrar(dados)).getMessage());
        verify(contaRepository, times(0)).save(any(Conta.class));
    }

}
