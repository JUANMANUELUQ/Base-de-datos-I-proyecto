package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;

public record LoanRequestInfoDTO(
        Long loanNumber,
        String emailEmployee,
        LocalDate requestDate,
        float amount,
        int periodMonths,
        String status
) {
}
