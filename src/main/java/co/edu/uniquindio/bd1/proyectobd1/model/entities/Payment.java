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
@Table(name = "payment")
public class Payment {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Column(name = "payment_number")
    private Integer paymentNumber; //Aquí irá el número de la cuota, recordar que no es un identificador
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "is_late")
    private boolean isLate;

    @Builder
    public Payment(Loan loan, Integer paymentNumber, LocalDate paymentDate, Double amount, boolean isLate) {
        this.loan = loan;
        this.paymentNumber = paymentNumber;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.isLate = isLate;
    }

}
