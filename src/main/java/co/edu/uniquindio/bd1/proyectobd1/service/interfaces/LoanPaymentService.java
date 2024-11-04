package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanPayment;

import java.util.List;
import java.util.Optional;

public interface LoanPaymentService {

    public Optional<LoanPayment> findById(Long id);

    public List<LoanPayment> findAll();

    public List<LoanPayment> findByLoanCode(Long code);

    public List<LoanPayment> findByEmployee(Long employeeCode);

    public void save(LoanPayment pago);

    public List<LoanPayment> findByEmployeeAndLoan(Long codeEmployee, Long loanNumber);

}
