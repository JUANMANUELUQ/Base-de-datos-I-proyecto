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
@Table(name = "loan_request")
public class LoanRequest {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "amount")
    private Double amount;
    @Column(name = "term")
    private Integer term;   // Plazo en meses (24, 36, 48, 60, 72)
    @Column(name = "request_date")
    private LocalDate requestDate;

    //@Enumerated(EnumType.STRING)
    //private LoanStatus status;

    @Builder
    public LoanRequest(Employee employee, Double amount, Integer term, LocalDate requestDate) {
        this.employee = employee;
        this.amount = amount;
        this.term = term;
        this.requestDate = requestDate;
        //this.status = status;
    }

}
