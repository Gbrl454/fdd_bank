package dtec.bank.api;

import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.utils.Moeda;
import dtec.bank.api.utils.Pais;
import dtec.bank.api.utils.TipoConta;

public abstract class ConfigTests implements MessageTests {
    protected DadosCadastroBanco getBanco() {
        return new DadosCadastroBanco("Banco", Pais.BRA);
    }

    protected DadosCadastroAgencia getAgencia() {
        return new DadosCadastroAgencia("Agencia", 1L);
    }

    protected DadosCadastroConta getContaNormal() {
        return new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.NORMAL,
                false, 0.0, false, 0.0);
    }

    protected DadosCadastroConta getContaEspecial() {
        return new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.ESPECIAL,
                true, 500.0, false, 0.0);
    }

    protected DadosCadastroConta getContaPremium() {
        return new DadosCadastroConta(1L, 1L, Moeda.USD, 500.0, TipoConta.PREMIUM,
                true, 500.0, true, 500.0);
    }

}
