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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "position")
    private String position; // Operario, Administrativo, Ejecutivo, Otros
    @Column(name = "salary")
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "employee")
    private List<LoanRequest> loanRequests;


    @Builder
    public Employee(String name, String position, Double salary, Branch branch, List<LoanRequest> loanRequests) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.branch = branch;
        this.loanRequests = loanRequests;
    }



}
