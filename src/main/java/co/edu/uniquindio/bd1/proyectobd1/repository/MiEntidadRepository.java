package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.MiEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiEntidadRepository extends JpaRepository<MiEntidad, Long> {

    // Consulta personalizada usando @Query
    @Query("SELECT e FROM MiEntidad e WHERE e.nombre = ?1")
    List<MiEntidad> findByNombrePersonalizado(String nombre);
}