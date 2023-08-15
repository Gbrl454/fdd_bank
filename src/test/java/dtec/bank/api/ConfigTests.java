package dtec.bank.api;

import dtec.bank.api.entity.Banco;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.utils.Pais;

public abstract class ConfigTests implements MessageTests {
    protected DadosCadastroBanco getBanco(){
        return new DadosCadastroBanco("Banco", Pais.BRA);
    }
}
