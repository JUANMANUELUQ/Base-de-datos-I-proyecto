package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;

public record LoanDTO(
        String id,
        String employeeId,
        Double amount,
        Double interestRate,
        LocalDate disbursementDate

) {
}
