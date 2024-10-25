package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;

public record EmployeeDTO(
        String login,
        LocalDate creationDate,
        String name,
        String email,
        boolean arrears,
        String position,
        float salary,
        String branch,
        String municipality
) {
}
