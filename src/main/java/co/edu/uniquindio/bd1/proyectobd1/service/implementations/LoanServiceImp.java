package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Loan;
import co.edu.uniquindio.bd1.proyectobd1.repository.LoanRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImp implements LoanService {

    @Autowired
    private LoanRepository loanRepo;

    public LoanServiceImp() {

    }

    @Override
    public Loan findById(Long id) {
        return null;
    }

    @Override
    public Loan createLoan(Loan loan) {
        return null;
    }

    @Override
    public Loan updateLoan(Loan loan) {
        return null;
    }

    @Override
    public void deleteLoan(Loan loan) {

    }

    @Override
    public List<Loan> findAll() {
        return List.of();
    }

    @Override
    public List<Loan> findByEmployee(Long id) {
        return List.of();
    }

    @Override
    public List<Loan> findByStatus(String status) {
        return List.of();
    }

    @Override
    public void disburseLoan(Loan loan) {

    }

    public void save(Loan loan) {
        loanRepo.save(loan);
    }
}
