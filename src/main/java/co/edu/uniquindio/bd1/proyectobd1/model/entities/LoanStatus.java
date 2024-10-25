package co.edu.uniquindio.bd1.proyectobd1.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "LoanStatus")
public class LoanStatus {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(name = "name")
    private String name;

    @Builder
    public LoanStatus(String name) {
        this.name = name;
    }

}
