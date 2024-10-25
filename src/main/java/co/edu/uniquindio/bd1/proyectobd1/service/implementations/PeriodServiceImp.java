package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodServiceImp {

    @Autowired
    private PeriodRepository periodRepo;

    public PeriodServiceImp() {

    }

}
