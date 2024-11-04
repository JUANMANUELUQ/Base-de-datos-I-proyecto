package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequest;

import java.util.List;
import java.util.Optional;

public interface LoanRequestService {

    public List<LoanRequest> findAll();

    public List<LoanRequest> findByEmployee(Long employeeId);

    public void save(LoanRequest loanRequest);

    public Optional<LoanRequest> findByLoanNumber(Long loanNumber);

}
