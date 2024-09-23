package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.repository.ReportRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportRepository reportRepo;

    @Override
    public byte[] generateDefaulterReport() {
        return new byte[0];
    }

    @Override
    public byte[] generateTotalLoansByMunicipality() {
        return new byte[0];
    }

    @Override
    public byte[] generateTotalLoansByBranch() {
        return new byte[0];
    }

    @Override
    public byte[] generateEmployeeLoanReport(Long employeeId) {
        return new byte[0];
    }

    @Override
    public byte[] generateLoanSummaryReport(Long loanId) {
        return new byte[0];
    }
}
