
package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequest;
import co.edu.uniquindio.bd1.proyectobd1.repository.LoanRequestRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanRequestServiceImp implements LoanRequestService {

    @Autowired
    private LoanRequestRepository loanRequestRepo;

    public LoanRequestServiceImp() {

    }

    public List<LoanRequest> findAll() {
        return loanRequestRepo.findAll();
    }

    public List<LoanRequest> findByEmployee(Long employeeId) {
        return loanRequestRepo.findByEmployee(employeeId);
    }

    public void save(LoanRequest loanRequest) {
        loanRequestRepo.save(loanRequest);
    }

    public Optional<LoanRequest> findByLoanNumber(Long loanNumber) {
        return loanRequestRepo.findById(loanNumber);
    }
}