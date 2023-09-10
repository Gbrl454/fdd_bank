package dtec.bank.api.repository;

import dtec.bank.api.entity.Agencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoAgencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
    Agencia findByNome(String nome);

    @Query("""
            SELECT a FROM Agencia  a
            WHERE a.banco.id = :idBanco
            """)
    List<DadosDetalhamentoAgencia> findAllByIdBanco(Long idBanco);
}
