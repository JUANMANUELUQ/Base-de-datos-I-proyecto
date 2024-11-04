package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {

    public List<Loan> findAll();

    public List<Loan> findByEmployee(Long code);

    public void save(Loan loan);

    public Optional<Loan> findByCode(Long code);

    public List<Loan> findByMunicipality(String municipality);

    public List<Loan> findByBranch(String branch);
}
