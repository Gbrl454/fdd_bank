package dtec.bank.api.entity;

import dtec.bank.api.utils.TipoConta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
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
    private Double saldo;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

    private Boolean cartao_de_credito;
    private Double saldo_cartao_de_credito;
    private Boolean lis;
    private Double saldo_lis;
}
