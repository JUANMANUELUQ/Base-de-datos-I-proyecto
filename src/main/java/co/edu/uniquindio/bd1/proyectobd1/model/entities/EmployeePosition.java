package co.edu.uniquindio.bd1.proyectobd1.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "EmployeePosition")
public class EmployeePosition {

    @EqualsAndHashCode.Include
    @Id
    private Long code;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "salary")
    private float salary;
    @Column(name = "cap", nullable = false)
    private float cap;

    @Builder
    public EmployeePosition(String name, float salary, float cap) {
        this.name = name;
        this.salary = salary;
        this.cap = cap;
    }
}
