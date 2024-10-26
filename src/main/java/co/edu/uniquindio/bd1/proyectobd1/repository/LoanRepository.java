package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    /*
    @Query("SELECT l FROM Loan l WHERE l.employee.id = :employeeId")
    List<Loan> findByEmployee(@Param("employeeId") Long employeeId);
     */

    @Query("SELECT l FROM Loan l WHERE l.employee.code = :code")
    List<Loan> findByEmployee(@Param("code") Long code);

    @Query("SELECT l FROM Loan l WHERE l.code = :code")
    Optional<Loan> findByCode(@Param("code") Long code);

}
