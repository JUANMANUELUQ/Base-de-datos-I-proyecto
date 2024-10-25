package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequestStatus;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.Period;
import co.edu.uniquindio.bd1.proyectobd1.repository.LoanRequestStatusRepository;
import co.edu.uniquindio.bd1.proyectobd1.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoanRequestStatusServiceImp {

    @Autowired
    private LoanRequestStatusRepository loanRequestStatusRepository;

    public LoanRequestStatusServiceImp() {

    }

    public void save(LoanRequestStatus loanRequestStatus) {
        loanRequestStatusRepository.save(loanRequestStatus);
    }

    public Optional<LoanRequestStatus> findByName(String name) {
        return loanRequestStatusRepository.findByName(name);
    }
}
