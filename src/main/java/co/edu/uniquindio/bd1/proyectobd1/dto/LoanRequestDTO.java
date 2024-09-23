package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;

public record LoanRequestDTO(
        String id,
        String employeeId,
        Double amount,
        Integer term,
        String status,
        LocalDate requestDate
) {
}
