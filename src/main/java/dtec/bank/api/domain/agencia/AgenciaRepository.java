package dtec.bank.api.domain.agencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
    Agencia findByNome (String nome);
}
