package dtec.bank.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
    private LocalDateTime horario_tranferencia;
    private Boolean sucesso;

    @PrePersist
    public void setHorario_tranferencia(){
        this.horario_tranferencia = LocalDateTime.now(ZoneId.of("UTC-0"));
    }
}
