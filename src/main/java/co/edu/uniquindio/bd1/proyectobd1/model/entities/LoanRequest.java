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
@Table(name = "loanRequest")
public class LoanRequest {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanNumber;
    @Column(name = "requestDate")
    private LocalDate requestDate;
    @Column(name = "requestedAmount")
    private float requestedAmount;
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "loanStatus")
    private LoanStatus loanStatus;
    @ManyToOne
    @JoinColumn(name = "period")
    private Period period;

    @Builder
    public LoanRequest(LocalDate requestDate, float requestedAmount, Employee employee, LoanStatus loanStatus, Period period) {
        this.requestDate = requestDate;
        this.requestedAmount = requestedAmount;
        this.employee = employee;
        this.loanStatus = loanStatus;
        this.period = period;
    }
}
