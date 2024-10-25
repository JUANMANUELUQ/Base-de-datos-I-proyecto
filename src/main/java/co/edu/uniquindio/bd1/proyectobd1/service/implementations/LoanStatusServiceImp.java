package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequestStatus;
import co.edu.uniquindio.bd1.proyectobd1.repository.LoanRequestStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanStatusServiceImp {

    @Autowired
    private LoanRequestStatusRepository loanStatusRepo;

    public LoanStatusServiceImp() {

    }

    public void save(LoanRequestStatus loanRequestStatus) {
        loanStatusRepo.save(loanRequestStatus);
    }
}
