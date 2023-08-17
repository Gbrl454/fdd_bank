package dtec.bank.api.service;

import dtec.bank.api.ConfigTests;
import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.Transferencia;
import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoMoeda;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.TransferenciaRepository;
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
import static org.mockito.Mockito.*;

class TransferenciaServiceTest extends ConfigTests {
    @InjectMocks
    private TransferenciaService transferenciaService;
    @Mock
    private BankLocateResolver locateResolver;
    @Mock
    private MessageSource messageSource;
    @Mock
    private HttpServletRequest request;
    @Mock
    private ContaRepository contaRepository;
    @Mock
    private TransferenciaRepository transferenciaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
    }

    @Test
    @DisplayName("Cadastrando Transferência com dados válidos")
    void testCadastrarTransferencia() {
        Conta contaOrigem = new Conta(1L, null, null,
                Moeda.USD, 5000000L, TipoConta.NORMAL, false, 0L, false, 0L);
        Conta contaDestino = new Conta(2L, null, null,
                Moeda.BOB, 50000L, TipoConta.NORMAL, false, 0L, false, 0L);
        DadosCadastroTransferencia dados = new DadosCadastroTransferencia(contaOrigem.getId(), contaDestino.getId(), 100.0);

        when(contaRepository.existsById(contaOrigem.getId())).thenReturn(true);
        when(contaRepository.existsById(contaDestino.getId())).thenReturn(true);
        when(contaRepository.getReferenceById(contaOrigem.getId())).thenReturn(contaOrigem);
        when(contaRepository.getReferenceById(contaDestino.getId())).thenReturn(contaDestino);

        Transferencia transferencia = new Transferencia(null, contaOrigem, contaDestino, 100L);
        when(transferenciaRepository.save(any())).thenReturn(transferencia);

        DadosDetalhamentoTransferencia resultado = transferenciaService.cadastrar(dados);

        assertNotNull(resultado);
        assertTrue(resultado.sucesso());
        assertNull(resultado.motivo());
        assertEquals((long) (contaOrigem.getMoeda().getMultiplicador() * dados.valor()), resultado.valor());
        assertEquals(contaOrigem.getId(), resultado.idContaOrigem());
        assertEquals(contaDestino.getId(), resultado.idContaDestino());
        assertEquals(new DadosDetalhamentoMoeda(contaOrigem.getMoeda()), resultado.moeda());
        verify(transferenciaRepository, times(1)).save(any(Transferencia.class));
    }

    @Test
    @DisplayName("Cadastrando Transferência com ID de Conta de Origem inexistente")
    void testCadastrarTransferenciaContaDeOrigemInvalido() {
        DadosCadastroTransferencia dados = new DadosCadastroTransferencia(1L, 2L, 500.0);

        when(contaRepository.existsById(dados.idOConta())).thenReturn(false);
        when(contaRepository.getReferenceById(dados.idOConta())).thenReturn(null);
        when(messageSource.getMessage("conta.origem.notexist", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaOrigemNotexist);

        assertEquals(
                contaOrigemNotexist,
                assertThrows(ValidacaoException.class, () -> transferenciaService.cadastrar(dados)).getMessage());
        verify(transferenciaRepository, times(0)).save(any(Transferencia.class));
    }

    @Test
    @DisplayName("Cadastrando Transferência com ID de Conta de Destino inexistente")
    void testCadastrarTransferenciaContaDeDestinoInvalido() {
        DadosCadastroTransferencia dados = new DadosCadastroTransferencia(1L, 2L, 500.0);
        when(contaRepository.existsById(dados.idOConta())).thenReturn(true);
        when(contaRepository.getReferenceById(dados.idOConta())).thenReturn(new Conta());
        when(contaRepository.existsById(dados.idDConta())).thenReturn(false);
        when(contaRepository.getReferenceById(dados.idDConta())).thenReturn(null);
        when(messageSource.getMessage("conta.destino.notexist", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaDestinoNotexist);

        assertEquals(
                contaDestinoNotexist,
                assertThrows(ValidacaoException.class, () -> transferenciaService.cadastrar(dados)).getMessage());
        verify(transferenciaRepository, times(0)).save(any(Transferencia.class));
    }

    @Test
    @DisplayName("Cadastrando Transferência com ID de Conta de Origem igual ao de Destino")
    void testCadastrarTransferenciaContaDeOrigemEDestinoIguais() {
        DadosCadastroTransferencia dados = new DadosCadastroTransferencia(3L, 3L, 500.0);
        when(contaRepository.existsById(dados.idOConta())).thenReturn(true);
        when(contaRepository.getReferenceById(dados.idOConta())).thenReturn(new Conta());
        when(messageSource.getMessage("conta.origemdestinoequals", null, locateResolver.resolveLocale(request)))
                .thenReturn(contaOrigemDestinoequals);

        assertEquals(dados.idOConta(), dados.idDConta());
        assertEquals(
                contaOrigemDestinoequals,
                assertThrows(ValidacaoException.class, () -> transferenciaService.cadastrar(dados)).getMessage());
        verify(transferenciaRepository, times(0)).save(any(Transferencia.class));
    }


//    @Test
//    @DisplayName("Removendo Saldo de Conta do tipo NORMAL")
//    void testRemoverSaldoContaNormal() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Conta do tipo NORMAL, que não possui Saldo")
//    void testRemoverSaldoContaNormalSemSaldo() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Conta do tipo ESPECIAL")
//    void testRemoverSaldoContaEspecial() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo e Saldo de Cartão de Cretido de Conta do tipo ESPECIAL")
//    void testRemoverSaldoESaldoCartaoDeCreditoContaEspecial() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Cartão de Cretido de Conta do tipo ESPECIAL")
//    void testRemoverSaldoCartaoDeCreditoContaEspecial() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Conta do tipo ESPECIAL, que não possui Saldos")
//    void testRemoverSaldoContaEspecialSemSaldos() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Conta do tipo PREMIUM")
//    void testRemoverSaldoContaPremium() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Cartão de Cretido de Conta do tipo PREMIUM")
//    void testRemoverSaldoCartaoDeCreditoContaPremium() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Cheque Especial de Conta do tipo PREMIUM")
//    void testRemoverSaldoChequeEspecialContaPremium() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo e Saldo de Cartão de Cretido de Conta do tipo PREMIUM")
//    void testRemoverSaldoESaldoCartaoDeCreditoContaPremium() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo e Saldo de Cheque Especial de Conta do tipo PREMIUM")
//    void testRemoverSaldoESaldoChequeEspecialContaPremium() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Cartão de Cretido e Saldo de Cheque Especial de Conta do tipo PREMIUM")
//    void testRemoverSaldoCartaoDeCreditoESaldoChequeEspecialContaPremium() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo, Saldo de Cartão de Cretido e Saldo de Cheque Especial de Conta do tipo PREMIUM")
//    void testRemoverSaldoESaldoCartaoDeCreditoESaldoChequeEspecialContaPremium() {
//    }

//    @Test
//    @DisplayName("Removendo Saldo de Conta do tipo PREMIUM, que não possui Saldos")
//    void testRemoverSaldoContaPremiumSemSaldos() {
//    }

}
