package dtec.bank.api.domain.banco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    Banco findByNome (String nome);
}
