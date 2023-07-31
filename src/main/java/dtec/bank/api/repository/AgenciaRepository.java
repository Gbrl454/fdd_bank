package dtec.bank.api.repository;

import dtec.bank.api.entity.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
    Agencia findByNome (String nome);
}
