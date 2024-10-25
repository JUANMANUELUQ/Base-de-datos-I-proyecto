package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequestStatus;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.Period;
import co.edu.uniquindio.bd1.proyectobd1.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImp {

    @Autowired
    private PeriodRepository periodRepo;

    public PeriodServiceImp() {

    }

    public List<Period> findAll() {
        return periodRepo.findAll();
    }

    public void save(Period period) {
        periodRepo.save(period);
    }

    public Optional<Period> findByPeriodMonths(int period) {
        return periodRepo.findByPeriodMonths(period);
    }
}
