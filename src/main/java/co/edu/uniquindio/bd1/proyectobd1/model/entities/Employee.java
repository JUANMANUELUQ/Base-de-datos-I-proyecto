package co.edu.uniquindio.bd1.proyectobd1.model.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "employee")
public class Employee{

    @EqualsAndHashCode.Include
    @Id
    private Long code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email; // Operario, Administrativo, Ejecutivo, Otros
    @Column(name = "arrears")
    private boolean arrears;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "employeePosition", nullable = false)
    private EmployeePosition employeePosition;

    @Builder
    public Employee(String name, String email, boolean arrears, User user, Branch branch,
                    EmployeePosition employeePosition) {
        this.name = name;
        this.email = email;
        this.arrears = arrears;
        this.user = user;
        this.branch = branch;
        this.employeePosition = employeePosition;
    }
}
