package dtec.bank.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
    @JoinColumn(name = "o_conta_id")
    private Conta oConta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_conta_id")
    private Conta dConta;

    private Long valor;
    private LocalDateTime horario_tranferencia;
    private Boolean sucesso;
    private String motivo;

    public Transferencia(Long id, Conta oConta, Conta dConta, Long valor, String motivo) {
        this.id = id;
        this.oConta = oConta;
        this.dConta = dConta;
        this.valor = valor;
        this.sucesso = false;
        this.motivo = motivo;
    }

    public Transferencia(Long id, Conta oConta, Conta dConta, Long valor) {
        this.id = id;
        this.oConta = oConta;
        this.dConta = dConta;
        this.valor = valor;
        this.sucesso = true;
    }

    @PrePersist
    public void setHorario_tranferencia() {
        this.horario_tranferencia = LocalDateTime.now(ZoneId.of("UTC-0"));
    }


    public void setHorario_tranferencia(LocalDateTime localDateTime) {
        this.horario_tranferencia = localDateTime;
    }
}
