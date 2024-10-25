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
    @Column(name = "creationDate")
    private LocalDate creationDate;
    @OneToOne
    @JoinColumn(name = "request")
    private LoanRequest request;

    @Builder
    public Loan(LocalDate creationDate, LoanRequest request) {
        this.creationDate = creationDate;
        this.request = request;
    }
}
