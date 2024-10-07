package co.edu.uniquindio.bd1.proyectobd1.dto;

public record EmployeeUpdateDTO(
        String login,
        String password,
        Long code,
        String name,
        String email,
        String position,
        String municipality,
        String branch,
        String oldLogin,
        Long oldCode,
        String oldEmail
) {
}
