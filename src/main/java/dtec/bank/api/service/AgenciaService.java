package dtec.bank.api.service;

import dtec.bank.api.entity.Agencia;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoAgencia;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AgenciaService {
    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    BancoRepository bancoRepository;

    public DadosDetalhamentoAgencia cadastrar (DadosCadastroAgencia dados) {
        if (dados.idBanco() != null && !bancoRepository.existsById(dados.idBanco())) {
            throw new ValidacaoException("ID do Banco informado não existe!");
        }

        var banco = bancoRepository.getReferenceById(dados.idBanco());
        if (banco == null) {
            throw new ValidacaoException("ID de Banco informado inválido!");
        }

        var agencia = new Agencia(null, dados.nome(), banco);

        if (agenciaRepository.findByNome(agencia.getNome()) != null) {
            throw new DataIntegrityViolationException("Já existe uma Agência com esse Nome");
        }

        agenciaRepository.save(agencia);
        return new DadosDetalhamentoAgencia(agencia);
    }
}
