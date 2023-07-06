package dtec.bank.api.domain.agencia;

import dtec.bank.api.domain.banco.Banco;
import dtec.bank.api.domain.banco.DadosCadastroBanco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "agencias")
@Entity(name = "Agencia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_id")
    private Banco idBanco;

}
