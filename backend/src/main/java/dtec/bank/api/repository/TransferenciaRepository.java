package dtec.bank.api.repository;

import dtec.bank.api.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query("""
            SELECT t from Transferencia t
            WHERE t.oConta.id = :idOConta
             """)
    List<Transferencia> findAllByIdOConta(Long idOConta);
}
