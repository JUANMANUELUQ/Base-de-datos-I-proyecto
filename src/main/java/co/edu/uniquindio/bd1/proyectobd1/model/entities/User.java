package co.edu.uniquindio.bd1.proyectobd1.model.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "userEntity")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "creationDate")
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "userType", nullable = false)
    private UserType userType;


    @Builder
    public User(String login, String password, LocalDate creationDate, UserType userType) {
        this.login = login;
        this.password = password;
        this.creationDate = creationDate;
        this.userType = userType;
    }
}
