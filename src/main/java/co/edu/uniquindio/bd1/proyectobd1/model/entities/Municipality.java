package co.edu.uniquindio.bd1.proyectobd1.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Municipality")
public class Municipality {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "population")
    private Long population;
    @Column(name = "description")
    private String description;

    @Builder

    public Municipality(String name, Long population, String description) {
        this.name = name;
        this.population = population;
        this.description = description;
    }
}
