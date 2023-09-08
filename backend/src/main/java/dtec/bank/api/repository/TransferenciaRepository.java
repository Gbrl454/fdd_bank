package dtec.bank.api.repository;

import dtec.bank.api.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {


    //    SELECT t.id, t.o_conta_id, t.d_conta_id, t.valor, t.horario_tranferencia, t.sucesso, t.motivo FROM sbancario.transferencias t,sbancario.contas c, sbancario.usuarios u
//    WHERE u.id = 2
//    AND u.id = c.usuario_id
//    AND (t.o_conta_id = c.id OR t.d_conta_id = c.id)
    @Query("""
            SELECT t FROM Transferencia t, Conta c, Usuario u
            WHERE u.id = :idUser
            AND u.id = c.usuario.id
            AND (t.oConta.id = c.id OR t.dConta.id = c.id)
              """)
    List<Transferencia> findAllByIdOConta(Long idUser);
}
