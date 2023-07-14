package dtec.bank.api.domain.conta;

import dtec.bank.api.domain.agencia.Agencia;
import dtec.bank.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

    private Boolean cartao_de_credito;
    private BigDecimal saldo_cartao_de_credito;
    private Boolean lis;
    private BigDecimal saldo_lis;
}
