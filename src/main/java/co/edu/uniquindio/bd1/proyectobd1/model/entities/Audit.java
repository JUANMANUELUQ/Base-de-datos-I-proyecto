package co.edu.uniquindio.bd1.proyectobd1.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Audit")
public class Audit {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryNumber;
    @Column(name = "entryDate")
    private LocalDate entryDate;
    @Column(name = "entryTime")
    private LocalTime entryTime;
    @Column(name = "outputDate")
    private LocalDate outputDate;
    @Column(name = "outputTime")
    private LocalTime outputTime;
    @ManyToOne
    @JoinColumn(name = "login", nullable = false)
    private User login;

    @Builder
    public Audit(LocalDate entryDate, LocalTime entryTime, LocalDate outputDate, LocalTime outputTime, User login) {
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.outputDate = outputDate;
        this.outputTime = outputTime;
        this.login = login;
    }
}
