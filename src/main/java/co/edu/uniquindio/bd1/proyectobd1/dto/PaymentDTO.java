package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;

public record PaymentDTO(
        String loanId,
        Integer paymentNumber,
        LocalDate paymentDate,
        Double amount,
        Boolean latePayment
) {
}
