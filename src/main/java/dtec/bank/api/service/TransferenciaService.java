package dtec.bank.api.service;

import dtec.bank.api.entity.dto.DadosCadastroAgencia;
import dtec.bank.api.entity.dto.DadosCadastroTransferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    @Autowired
    BancoRepository bancoRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    ContaRepository contaRepository;


    public DadosDetalhamentoTransferencia cadastrar (DadosCadastroTransferencia dados) {

        if (dados.idOBanco() != null && !bancoRepository.existsById(dados.idOBanco())) {
            throw new ValidacaoException(ErrorMessage.idBancoOrigemNotExist);
        }

        if (dados.idOBanco() != null && !bancoRepository.existsById(dados.idOBanco())) {
            throw new ValidacaoException(ErrorMessage.idBancoDestinoNotExist);
        }



        return null;
    }
}
