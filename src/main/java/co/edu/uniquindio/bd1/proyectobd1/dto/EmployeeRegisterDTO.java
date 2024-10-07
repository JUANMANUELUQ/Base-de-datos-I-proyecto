package co.edu.uniquindio.bd1.proyectobd1.dto;

public record EmployeeRegisterDTO(
        String login,
        String password,
        String code,
        String name,
        String email,
        String position,
        String municipality,
        String branch
) {
}
