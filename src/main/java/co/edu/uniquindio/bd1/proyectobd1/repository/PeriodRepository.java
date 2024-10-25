package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long>  {
}
