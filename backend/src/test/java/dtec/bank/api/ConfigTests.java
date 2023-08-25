package dtec.bank.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.utils.Moeda;
import dtec.bank.api.utils.Pais;
import dtec.bank.api.utils.TipoConta;

public abstract class ConfigTests implements MessageTests {
    protected DadosCadastroBanco getDadosCadastroBanco() {
        return new DadosCadastroBanco("Banco", Pais.BRA);
    }

    protected DadosCadastroAgencia getDadosCadastroAgencia() {
        return new DadosCadastroAgencia("Agencia", 1L);
    }

    protected DadosCadastroConta getDadosCadastroContaNormal() {
        return new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.NORMAL,
                false, 0.0, false, 0.0);
    }

    protected DadosCadastroConta getDadosCadastroContaEspecial() {
        return new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.ESPECIAL,
                true, 500.0, false, 0.0);
    }

    protected DadosCadastroConta getDadosCadastroContaPremium() {
        return new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.PREMIUM,
                true, 500.0, true, 500.0);
    }


    protected String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
