package dtec.bank.api.repository;

import dtec.bank.api.entity.Conta;
import dtec.bank.api.entity.dto.DadosDetalhamentoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query("""
            SELECT c FROM Conta  c
            WHERE c.usuario.id = :idUser
            """)
    List<DadosDetalhamentoConta> findAllByIdUsuario(Long idUser);
}
