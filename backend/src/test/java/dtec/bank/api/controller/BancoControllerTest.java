package dtec.bank.api.controller;

import dtec.bank.api.ConfigTests;
import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.service.BancoService;
import dtec.bank.api.utils.Pais;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BancoControllerTest extends ConfigTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BancoService bancoService;
    @Autowired
    private JacksonTester<DadosCadastroBanco> dadosCadastroBancoJson;

    @Test
    @DisplayName("Cadastrando Banco e retornando detalhamento")
    @WithMockUser
    public void testPostCadastrarBanco() throws Exception {
        var response = mvc.perform(
                post("/bancos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroBancoJson.write(
                                new DadosCadastroBanco("Banco", Pais.BRA)
                        ).getJson())).andReturn().getResponse();
        System.out.println(response);
    }
}
