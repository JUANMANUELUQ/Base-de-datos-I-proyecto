package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Parra cambiar contraseña
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void changePassword(@Param("id") Long id, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.login = :login")
    Optional<User> findByLogin(@Param("login") String login);


}
