package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Long> {

    @Query("SELECT p FROM LoanPayment p WHERE p.id = :id")
    Optional<LoanPayment> findById(@Param("id")Long id);

    @Query("SELECT p FROM LoanPayment p WHERE p.loan.code = :loanCode")
    List<LoanPayment> findByLoanCode(@Param("loanCode")Long loanCode);

    @Query("SELECT p FROM LoanPayment p WHERE p.loan.employee.code = :employeeCode")
    List<LoanPayment> findByEmployeeCode(@Param("employeeCode")Long employeeCode);

    @Query("SELECT p FROM LoanPayment p WHERE p.loan.employee.code = :employeeCode AND p.loan.code=:loanNumber")
    List<LoanPayment> findByEmployeeAndLoan(@Param("employeeCode")Long employeeCode, @Param("loanNumber")Long loanNumber);

   /*
    @Query("SELECT p FROM LoanPayment p WHERE p.id = :loanId")
    List<LoanPayment> findByLoanId(@Param("loanId") Long loanId);

    @Query("SELECT p FROM LoanPayment p WHERE p.paymentDate > :latePaymentDate")
    List<LoanPayment> findLatePayments(@Param("latePaymentDate") LocalDate latePaymentDate);

    @Query("SELECT SUM(p.amount) FROM LoanPayment p WHERE p.loan.id = :loanId")
    BigDecimal calculateRemainingDebt(@Param("loanId") Long loanId);
    */

}
