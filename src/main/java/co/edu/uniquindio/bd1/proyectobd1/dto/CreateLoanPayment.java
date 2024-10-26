package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;

public record CreateLoanPayment(
        Long loanNumber,
        int paymentNumber,
        LocalDate paymentDate,
        float value
) {
}
