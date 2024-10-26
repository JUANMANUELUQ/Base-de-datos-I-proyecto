package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;

public record LoanDTO(
        Long numberLoan,
        LocalDate creationDate,
        float amount,
        int periodMonths,
        float interestRate,
        Long employeeCode
) {
}
