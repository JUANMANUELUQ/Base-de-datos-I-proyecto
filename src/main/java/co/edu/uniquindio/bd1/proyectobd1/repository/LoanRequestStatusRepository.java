package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRequestStatusRepository extends JpaRepository<LoanRequestStatus, Long>  {

    @Query("SELECT l FROM LoanRequestStatus l WHERE l.name=:name")
    public Optional<LoanRequestStatus> findByName(@Param("name") String name);

}
