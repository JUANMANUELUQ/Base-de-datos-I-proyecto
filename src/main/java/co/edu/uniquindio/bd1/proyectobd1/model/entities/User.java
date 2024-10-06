package co.edu.uniquindio.bd1.proyectobd1.model.entities;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "UserEntity")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "creationDate")
    private String creationDate;
    @ManyToOne
    @JoinColumn(name = "userType", nullable = false)
    private UserType userType;


    @Builder
    public User(String login, String password, String creationDate, UserType userType) {
        this.login = login;
        this.password = password;
        this.creationDate = creationDate;
        this.userType = userType;
    }
}
