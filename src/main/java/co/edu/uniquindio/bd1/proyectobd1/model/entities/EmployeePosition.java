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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private float salary;
    @Column(name = "cap")
    private float cap;

    @Builder
    public EmployeePosition(String name, float salary, float cap) {
        this.name = name;
        this.salary = salary;
        this.cap = cap;
    }
}
