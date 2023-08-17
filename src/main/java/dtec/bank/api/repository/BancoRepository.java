package dtec.bank.api.repository;

import dtec.bank.api.entity.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    Banco findByNome(String nome);
}
