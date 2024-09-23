package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequest;

import java.util.List;

public interface LoanRequestService {
    LoanRequest findById(Long id);
    LoanRequest createLoanRequest(LoanRequest loanRequest);
    LoanRequest updateLoanRequest(LoanRequest loanRequest);
    void deleteLoanRequest(Long id);
    List<LoanRequest> findAll();
    List<LoanRequest> findByEmployee(Long employeeId);
    List<LoanRequest> findByStatus(String status);
    boolean approveLoanRequest(LoanRequest loanRequest);
    boolean rejectLoanRequest(LoanRequest loanRequest);
}
