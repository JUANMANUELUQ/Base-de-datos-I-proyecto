package co.edu.uniquindio.bd1.proyectobd1.model.entities;


import co.edu.uniquindio.bd1.proyectobd1.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "user")
    private List<LoginAudit> auditLog;

    @Builder
    public User(String username, String password, UserRole role, boolean isActive, List<LoginAudit> auditLog) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.auditLog = auditLog;
    }


}
