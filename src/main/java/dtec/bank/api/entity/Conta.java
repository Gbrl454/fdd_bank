package dtec.bank.api.entity;

import dtec.bank.api.utils.Moeda;
import dtec.bank.api.utils.TipoConta;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agencia_id")
    private Agencia agencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Moeda moeda;

    private Long saldo;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

    private Boolean cartao_de_credito;
    private Long saldo_cartao_de_credito;
    private Boolean lis;
    private Long saldo_lis;
}
