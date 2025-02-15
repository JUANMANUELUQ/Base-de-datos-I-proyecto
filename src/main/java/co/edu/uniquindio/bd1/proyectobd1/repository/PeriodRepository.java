package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long>  {

    @Query("SELECT p FROM Period p WHERE p.periodMonths=:periodMonths")
    public Optional<Period> findByPeriodMonths(@Param("periodMonths") int periodMonths);

}
