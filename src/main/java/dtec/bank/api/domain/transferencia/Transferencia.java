package dtec.bank.api.domain.transferencia;

import dtec.bank.api.domain.agencia.Agencia;
import dtec.bank.api.domain.banco.Banco;
import dtec.bank.api.domain.conta.Conta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

// TODO pesquisar
//@PrePersist
//@PreUpdate
@Table(name = "transferencias")
@Entity(name = "Transferencia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "o_banco_id")
    private Banco oBanco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "o_agencia_id")
    private Agencia oAgencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "o_conta_id")
    private Conta oConta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_banco_id")
    private Banco dBanco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_agencia_id")
    private Agencia dAgencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_conta_id")
    private Conta dConta;

    private Double valor;
    private Timestamp horario_tranferencia;
    private Boolean sucesso;
}
