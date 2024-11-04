package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Loan;
import co.edu.uniquindio.bd1.proyectobd1.repository.LoanRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImp implements LoanService {

    @Autowired
    private LoanRepository loanRepo;

    public LoanServiceImp() {

    }

    public List<Loan> findAll() {
        return loanRepo.findAll();
    }

    public List<Loan> findByEmployee(Long code) {
        return loanRepo.findByEmployee(code);
    }

    public void save(Loan loan) {
        boolean execute=true;
        if (loan.getCode()==null) {
            Long primaryKey=0L;
            while(execute) {
                primaryKey=(long)(Math.random()*Long.MAX_VALUE);
                if (findByCode(primaryKey).isEmpty()) {
                    execute=false;
                }
            }
            loan.setCode(primaryKey);
        }
        loanRepo.save(loan);
    }

    public Optional<Loan> findByCode(Long code) {
        return loanRepo.findByCode(code);
    }

    public List<Loan> findByMunicipality(String municipality) {
        return loanRepo.findByMunicipality(municipality);
    }

    public List<Loan> findByBranch(String branch) {
        return loanRepo.findByBranch(branch);
    }

}
