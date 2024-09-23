package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

    @Query("SELECT lr FROM LoanRequest lr WHERE lr.status = :status")
    List<LoanRequest> findByStatus(@Param("status") String status);

    @Query("SELECT lr FROM LoanRequest lr WHERE lr.employee.id = :employeeId")
    List<LoanRequest> findByEmployee(@Param("employeeId") Long employeeId);

}
