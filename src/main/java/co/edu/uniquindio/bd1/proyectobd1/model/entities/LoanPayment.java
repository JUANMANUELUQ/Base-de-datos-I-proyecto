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
@Table(name = "LoanPayment")
public class LoanPayment {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "payment_number", nullable = false)
    private Integer paymentNumber; //Aquí irá el número de la cuota de un prestamo, recordar que no es un identificador
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
    @Column(name = "value", nullable = false)
    private float value;
    @ManyToOne
    @JoinColumn(name = "loan", nullable = false)
    private Loan loan;


    @Builder
    public LoanPayment(Integer paymentNumber, LocalDate paymentDate, float value, Loan loan) {
        this.paymentNumber = paymentNumber;
        this.paymentDate = paymentDate;
        this.value = value;
        this.loan = loan;
    }
}
