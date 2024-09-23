package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

public interface ReportService {
    byte[] generateDefaulterReport();  // Reporte de empleados morosos
    byte[] generateTotalLoansByMunicipality();  // Total de préstamos por municipio
    byte[] generateTotalLoansByBranch();  // Total de préstamos por sucursal
    byte[] generateEmployeeLoanReport(Long employeeId);  // Estado de cuenta de un empleado
    byte[] generateLoanSummaryReport(Long loanId);  // Resumen de préstamo
}
