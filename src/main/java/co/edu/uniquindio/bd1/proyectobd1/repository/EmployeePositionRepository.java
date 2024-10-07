package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {

    @Query("SELECT e FROM EmployeePosition e WHERE e.name = :name")
    Optional<EmployeePosition> findByName(@Param("name") String name);

}
