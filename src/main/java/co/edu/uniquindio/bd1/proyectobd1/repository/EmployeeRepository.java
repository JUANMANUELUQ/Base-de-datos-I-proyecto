package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e")
    List<Employee> findAll();

    @Query("SELECT e FROM Employee e WHERE e.branch.id = :branchId")
    List<Employee> findByBranch(@Param("branchId") Long branchId);



}
