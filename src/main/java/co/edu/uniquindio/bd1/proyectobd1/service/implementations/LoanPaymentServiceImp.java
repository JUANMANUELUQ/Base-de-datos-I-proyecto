package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanPayment;
import co.edu.uniquindio.bd1.proyectobd1.repository.LoanPaymentRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.LoanPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanPaymentServiceImp implements LoanPaymentService {

    @Autowired
    private LoanPaymentRepository loanPaymentRepo;

    public LoanPaymentServiceImp(){

    }


    public LoanPayment findById(Long id) {
        return null;
    }


    public LoanPayment createPayment(LoanPayment payment) {
        return null;
    }


    public LoanPayment updatePayment(LoanPayment payment) {
        return null;
    }


    public void deletePayment(Long id) {

    }


    public List<LoanPayment> findAll() {
        return List.of();
    }

    public List<LoanPayment> findByLoanCode(Long code) {
        return loanPaymentRepo.findByLoanCode(code);
    }


    public List<LoanPayment> findLatePayment(Long id) {
        return List.of();
    }


    public Double calculateRemainingDebt(Long loanId) {
        return 0.0;
    }


    public List<LoanPayment> findByEmployee(Long employeeCode) {
        return loanPaymentRepo.findByEmployeeCode(employeeCode);
    }

    public void save(LoanPayment pago) {
        loanPaymentRepo.save(pago);
    }

    public List<LoanPayment> findByEmployeeAndLoan(Long codeEmployee, Long loanNumber) {
        return loanPaymentRepo.findByEmployeeAndLoan(codeEmployee, loanNumber);
    }
}
