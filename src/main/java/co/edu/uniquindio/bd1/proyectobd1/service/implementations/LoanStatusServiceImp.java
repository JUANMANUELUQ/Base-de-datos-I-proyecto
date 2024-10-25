package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.repository.LoanStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanStatusServiceImp {

    @Autowired
    private LoanStatusRepository loanStatusRepo;

    public LoanStatusServiceImp() {

    }

}
