package dtec.bank.api.repository;

import dtec.bank.api.entity.Transferencia;
import dtec.bank.api.entity.dto.DadosDetalhamentoTransferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    @Query("""
            SELECT t FROM Transferencia  t
            WHERE t.oConta.usuario.id = :idUser OR t.dConta.usuario.id = :idUser
            """)
    List<DadosDetalhamentoTransferencia> findAllByIdUsuario(Long idUser);
}
