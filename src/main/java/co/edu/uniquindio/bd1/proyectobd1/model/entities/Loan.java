package co.edu.uniquindio.bd1.proyectobd1.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Loan")
public class Loan {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(name = "amount", nullable = false)
    private float amount;
    @Column(name = "creationDate", nullable = false)
    private LocalDate creationDate;
    @OneToOne
    @JoinColumn(name = "request", nullable = false)
    private LoanRequest request;
    @ManyToOne
    @JoinColumn(name = "period")
    Period period;
    @ManyToOne
    @JoinColumn(name = "employee")
    Employee employee;


    @Builder
    public Loan(float amount, LocalDate creationDate, LoanRequest request, Period period, Employee employee) {
        this.amount = amount;
        this.creationDate = creationDate;
        this.request = request;
        this.period = period;
        this.employee = employee;
    }
}
