package co.edu.uniquindio.bd1.proyectobd1.dto;

public record ReportBranchTotalDTO(
        String branch,
        float total,
        int loansCuantity,
        int employeeCuantity
) {
}
