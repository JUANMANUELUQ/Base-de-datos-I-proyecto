package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    @Query("SELECT u FROM UserType u WHERE u.level=:level")
    public Optional<UserType> findByLevel(@Param("level") String level);

}
