package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Payment;

import java.util.List;

public interface PaymentService {
    Payment findById(Long id);
    Payment createPayment(Payment payment);
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);
    List<Payment> findAll();
    List<Payment> findByLoanId(Long id);
    List<Payment> findLatePayment(Long id);
    Double calculateRemainingDebt(Long loanId);
    List<Payment> findByEmployee(Long id);
}
