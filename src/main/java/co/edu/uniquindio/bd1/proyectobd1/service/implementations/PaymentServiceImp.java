package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Payment;
import co.edu.uniquindio.bd1.proyectobd1.repository.PaymentRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Override
    public Payment findById(Long id) {
        return null;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return null;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return null;
    }

    @Override
    public void deletePayment(Long id) {

    }

    @Override
    public List<Payment> findAll() {
        return List.of();
    }

    @Override
    public List<Payment> findByLoanId(Long id) {
        return List.of();
    }

    @Override
    public List<Payment> findLatePayment(Long id) {
        return List.of();
    }

    @Override
    public Double calculateRemainingDebt(Long loanId) {
        return 0.0;
    }

    @Override
    public List<Payment> findByEmployee(Long id) {
        return List.of();
    }
}
