package dtec.bank.api.service;

import dtec.bank.api.entity.Agencia;
import dtec.bank.api.entity.Banco;
import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.Usuario;
import dtec.bank.api.entity.dto.DadosCadastroConta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import dtec.bank.api.exception.ValidacaoException;
import dtec.bank.api.repository.AgenciaRepository;
import dtec.bank.api.repository.BancoRepository;
import dtec.bank.api.repository.ContaRepository;
import dtec.bank.api.repository.UsuarioRepository;
import dtec.bank.api.utils.BankLocateResolver;
import dtec.bank.api.utils.Moeda;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {
    @Autowired
    BankLocateResolver locateResolver;
    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    AgenciaRepository agenciaRepository;
    @Autowired
    BancoRepository bancoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    private boolean cartaoDeCredito = false;
    private long saldoCartaoDeCredito = 0L;
    private boolean lis = false;
    private long saldoLis = 0L;

    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public DadosDetalhamentoConta cadastrar(DadosCadastroConta dados) {
        return cadastrar(dados, null);
    }

    public DadosDetalhamentoConta cadastrar(DadosCadastroConta dados, Usuario logado) {
        Long idUser = (dados.idUsuario() == null) ? logado.getId() : dados.idUsuario();

        List<DadosDetalhamentoConta> contas = contaRepository.findAllByIdUsuario(idUser);

        if (contas.size() >= 3) throw new ValidacaoException(get("conta.register.max"));

        for (DadosDetalhamentoConta conta : contas)
            if (conta.tipo() == dados.tipo()) throw new ValidacaoException(get("conta.register.sameType"));

        if (!agenciaRepository.existsById(dados.idAgencia())) throw new ValidacaoException(get("agencia.id.notexist"));

        Agencia agencia = agenciaRepository.getReferenceById(dados.idAgencia());

        if (!bancoRepository.existsById(dados.idBanco())) throw new ValidacaoException(get("banco.id.notexist"));

        Banco banco = bancoRepository.getReferenceById(dados.idBanco());

        if (!usuarioRepository.existsById(idUser)) throw new ValidacaoException(get("usuario.id.notexist"));

        Usuario usuario = usuarioRepository.getReferenceById(idUser);

        Moeda moeda = banco.getPais().getMoeda();

        switch (dados.tipo()) {
            case NORMAL -> validaContaNormal(dados);
            case ESPECIAL -> validaContaEspecial(dados, moeda);
            case PREMIUM -> validaContaPremium(dados, moeda);
        }

        Long saldo = (long) (dados.saldo() * moeda.getMultiplicador());

        Conta conta = new Conta(null, dados.tipo(), saldo, cartaoDeCredito, saldoCartaoDeCredito, lis, saldoLis, agencia, banco, usuario);
        contaRepository.save(conta);
        return new DadosDetalhamentoConta(conta);
    }

    private void validaContaNormal(DadosCadastroConta dados) {
        if ((dados.cartao_de_credito() != null && dados.cartao_de_credito()) ||
                (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0))
            throw new ValidacaoException(get("conta.normal.notown.creditcard"));

        if ((dados.lis() != null && dados.lis()) ||
                (dados.saldo_lis() != null && dados.saldo_lis() > 0))
            throw new ValidacaoException(get("conta.normal.notown.lis"));
    }

    private void validaContaEspecial(DadosCadastroConta dados, Moeda moeda) {
        dadosCartaoDeCreditoNotNull(dados, moeda);
        if ((dados.lis() != null && dados.lis()) || (dados.saldo_lis() != null && dados.saldo_lis() > 0))
            throw new ValidacaoException(get("conta.especial.notown.lis"));
    }

    private void dadosCartaoDeCreditoNotNull(DadosCadastroConta dados, Moeda moeda) {
        if (dados.cartao_de_credito() != null) {
            cartaoDeCredito = dados.cartao_de_credito();

            if (cartaoDeCredito) {
                if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0)
                    saldoCartaoDeCredito = (long) (dados.saldo_cartao_de_credito() * moeda.getMultiplicador());
                else throw new ValidacaoException(get("saldo.creditcard.informwhenhave"));

            } else if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0)
                throw new ValidacaoException(get("saldo.creditcard.informonlyhave"));

        } else if (dados.saldo_cartao_de_credito() != null && dados.saldo_cartao_de_credito() > 0)
            throw new ValidacaoException(get("saldo.creditcard.informonlyhave"));
    }

    private void validaContaPremium(DadosCadastroConta dados, Moeda moeda) {
        dadosCartaoDeCreditoNotNull(dados, moeda);

        if (dados.lis() != null) {
            lis = dados.lis();

            if (lis) {
                if (dados.saldo_lis() != null && dados.saldo_lis() > 0)
                    saldoLis = (long) (dados.saldo_lis() * moeda.getMultiplicador());
                else throw new ValidacaoException(get("saldo.lis.informwhenhave"));

            } else if (dados.saldo_lis() != null && dados.saldo_lis() > 0)
                throw new ValidacaoException(get("saldo.lis.informonlyhave"));

        } else if (dados.saldo_lis() != null && dados.saldo_lis() > 0)
            throw new ValidacaoException(get("saldo.lis.informonlyhave"));
    }

    public List<DadosDetalhamentoConta> listAll(Usuario logado) {
        List<DadosDetalhamentoConta> list = contaRepository.findAllByIdUsuario(logado.getId());

        if (list.isEmpty()) throw new ValidacaoException(get("conta.list.empty.byidusuario"));

        return list;
    }

    public DadosDetalhamentoConta contaById(Long idConta) {
        if (!contaRepository.existsById(idConta)) throw new ValidacaoException(get("conta.id.notexist"));

        return new DadosDetalhamentoConta(contaRepository.getReferenceById(idConta));
    }
}
