package dtec.bank.api.entity;

import dtec.bank.api.entity.dto.DadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String login;
    private String senha;

    public Usuario (DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.login = dados.login();
        this.senha = dados.senha();
    }
}
