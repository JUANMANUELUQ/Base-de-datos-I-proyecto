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
@Table(name = "branch")
public class Branch {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;
    @Column(name = "deparment")
    private String department;

    @OneToMany(mappedBy = "branch")
    private List<Employee> employees;

    @Builder
    public Branch(String name, String city, String department, List<Employee> employees) {
        this.name = name;
        this.city = city;
        this.department = department;
        this.employees = employees;
    }

}
