package co.edu.uniquindio.bd1.proyectobd1.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//ESTA CLASE ES ALGO TEMPORAL QUE ESTOY PROBANDO PARA REGISTRAR EL ACCESO DE LOS USUARIOS AL SISTEMA
public class LoginAudit {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

    public LoginAudit(User user, LocalDateTime loginTime, LocalDateTime logoutTime) {
        this.user = user;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }


}
