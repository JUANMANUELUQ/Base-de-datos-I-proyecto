package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.LoanRequestStatus;

import java.util.Optional;

public interface LoanRequestStatusService {

    public void save(LoanRequestStatus loanRequestStatus);

    public Optional<LoanRequestStatus> findByName(String name);

}
