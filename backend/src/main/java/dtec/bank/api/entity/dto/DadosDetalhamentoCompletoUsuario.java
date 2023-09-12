package dtec.bank.api.entity.dto;

import dtec.bank.api.entity.Usuario;

import java.util.List;

public record DadosDetalhamentoCompletoUsuario(Long id, String nome,
                                               List<DadosDetalhamentoConta> detalhamentoContas) {
    public DadosDetalhamentoCompletoUsuario(Usuario usuario, List<DadosDetalhamentoConta> contas) {
        this(usuario.getId(), usuario.getNome(), contas);
    }

}
