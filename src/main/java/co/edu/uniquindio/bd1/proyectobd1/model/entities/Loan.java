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
@Table(name = "loan")
public class Loan {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "amount")
    private Double amount;
    @Column(name = "interest_rate")
    private Double interestRate;
    @Column(name = "disbursement_date")
    private LocalDate disbursementDate;

    @OneToMany(mappedBy = "loan")
    private List<Payment> payments;

    @Builder
    public Loan(Employee employee, Double amount, Double interestRate, LocalDate disbursementDate, List<Payment> payments) {
        this.employee = employee;
        this.amount = amount;
        this.interestRate = interestRate;
        this.disbursementDate = disbursementDate;
        this.payments = payments;
    }

}
