package dtec.bank.api.entity;

import dtec.bank.api.entity.dto.DadosCadastroBanco;
import dtec.bank.api.utils.Pais;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "bancos")
@Entity(name = "Banco")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    private Pais pais;

    public Banco(DadosCadastroBanco dados) {
        this.nome = dados.nome();
        this.pais = dados.pais();
    }
}
