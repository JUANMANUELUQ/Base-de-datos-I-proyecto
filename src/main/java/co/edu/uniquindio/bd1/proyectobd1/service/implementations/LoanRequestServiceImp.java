
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

    @Override
    public LoanRequest findById(Long id) {
        return null;
    }

    @Override
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        return null;
    }

    @Override
    public LoanRequest updateLoanRequest(LoanRequest loanRequest) {
        return null;
    }

    @Override
    public void deleteLoanRequest(Long id) {

    }

    @Override
    public List<LoanRequest> findAll() {
        return loanRequestRepo.findAll();
    }

    @Override
    public List<LoanRequest> findByEmployee(Long employeeId) {
        return loanRequestRepo.findByEmployee(employeeId);
    }

    @Override
    public List<LoanRequest> findByStatus(String status) {
        return List.of();
    }

    @Override
    public boolean approveLoanRequest(LoanRequest loanRequest) {
        return false;
    }

    @Override
    public boolean rejectLoanRequest(LoanRequest loanRequest) {
        return false;
    }

    public void save(LoanRequest loanRequest) {
        loanRequestRepo.save(loanRequest);
    }

    public Optional<LoanRequest> findByLoanNumber(Long loanNumber) {
        return loanRequestRepo.findById(loanNumber);
    }
}