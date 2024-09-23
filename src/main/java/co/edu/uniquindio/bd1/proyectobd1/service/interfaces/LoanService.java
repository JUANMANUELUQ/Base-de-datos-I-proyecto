package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Loan;

import java.util.List;

public interface LoanService {
    Loan findById(Long id);
    Loan createLoan(Loan loan);
    Loan updateLoan(Loan loan);
    void deleteLoan(Loan loan);
    List<Loan> findAll();
    List<Loan> findByEmployee(Long id);
    List<Loan> findByStatus(String status);
    void disburseLoan(Loan loan);
}
