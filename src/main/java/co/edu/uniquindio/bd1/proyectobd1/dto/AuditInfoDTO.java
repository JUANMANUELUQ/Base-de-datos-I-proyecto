package co.edu.uniquindio.bd1.proyectobd1.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AuditInfoDTO(
        LocalDate entryDate,
        LocalTime entryTime,
        LocalDate outputDate,
        LocalTime outputTime,
        String user
) {
}
