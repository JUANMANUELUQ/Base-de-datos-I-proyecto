package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;

public record ReportLoanRequestDTO(
        LocalDate date,
        String status,
        float amount
) {
}
