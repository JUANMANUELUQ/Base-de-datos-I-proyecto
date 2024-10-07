package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {

    @Query("SELECT m FROM Municipality m WHERE m.code=:code")
    public Optional<Municipality> findByCode(@Param("code") Long code);

    @Query("SELECT m FROM Municipality m WHERE m.name=:name")
    public Optional<Municipality> findByName(@Param("name") String name);

}
