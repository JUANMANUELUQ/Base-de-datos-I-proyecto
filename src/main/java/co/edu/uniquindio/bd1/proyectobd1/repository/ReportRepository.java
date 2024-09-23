package co.edu.uniquindio.bd1.proyectobd1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository {
   // @Query("SELECT e.municipality, SUM(l.amount) FROM Loan l JOIN l.employee e GROUP BY e.municipality")
   // List<Object[]> generateTotalLoansByMunicipality();

    @Query("SELECT e.branch.name, SUM(l.amount) FROM Loan l JOIN l.employee e GROUP BY e.branch.name")
    List<Object[]> generateTotalLoansByBranch();

  //  @Query("SELECT e, SUM(l.amount) FROM Loan l JOIN l.employee e WHERE e.isBehind = true GROUP BY e")
   // List<Object[]> generateDefaulterReport();



}
