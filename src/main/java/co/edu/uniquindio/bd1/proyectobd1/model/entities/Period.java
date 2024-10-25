package co.edu.uniquindio.bd1.proyectobd1.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Period")
public class Period {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(name = "interestRate")
    private float interestRate;
    @Column(name = "periodMonths", nullable = false, unique = true)
    private int periodMonths;

    @Builder
    public Period(float interestRate, int periodMonths) {
        this.interestRate = interestRate;
        this.periodMonths = periodMonths;
    }
}
