package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e")
    List<Employee> findAll();

    @Query("SELECT e FROM Employee e WHERE e.branch.codeBranch = :branchId")
    List<Employee> findByBranch(@Param("branchId") Long branchId);

    @Query("SELECT e FROM Employee e WHERE e.code = :code")
    Optional<Employee> findByCode(@Param("code") Long code);

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmail(@Param("email") String email);



}
