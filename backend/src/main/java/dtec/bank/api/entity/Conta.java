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
    @Enumerated(EnumType.STRING)
    private TipoConta tipo;
    private Long saldo;
    private Boolean cartaoDeCredito;
    private Long saldoCartaoDeCredito;
    private Boolean lis;
    private Long saldoLis;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agencia_id")
    private Agencia agencia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agencia_banco_id")
    private Banco banco;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void retirarSaldo(Long valor) {
        this.saldo -= valor;
    }

    public void retirarSaldoCartaoCeCredito(Long valor) {
        this.saldoCartaoDeCredito -= valor;
    }

    public void retirarSaldoLis(Long valor) {
        this.saldoLis -= valor;
    }

    public void zerarSaldo() {
        this.saldo = 0L;
    }

    public void zerarSaldoLis() {
        this.saldoLis = 0L;
    }

    public void adicionarSaldo(Long valor) {
        this.saldo += valor;
    }
}
