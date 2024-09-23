package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository {

    @Query("SELECT p FROM Payment p WHERE p.loan.id = :loanId")
    List<Payment> findByLoanId(@Param("loanId") Long loanId);

    @Query("SELECT p FROM Payment p WHERE p.paymentDate > :latePaymentDate")
    List<Payment> findLatePayments(@Param("latePaymentDate") LocalDate latePaymentDate);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.loan.id = :loanId")
    BigDecimal calculateRemainingDebt(@Param("loanId") Long loanId);
}
