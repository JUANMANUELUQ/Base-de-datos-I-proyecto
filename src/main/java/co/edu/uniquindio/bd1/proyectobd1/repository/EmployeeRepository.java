package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("SELECT e FROM Employee e WHERE e.user.login=:login")
    Optional<Employee> findByUser(@Param("login") String login);

    @Query("SELECT e FROM Employee e WHERE e.branch.municipality.name=:municipality")
    List<Employee> findByMunicipality(@Param("municipality") String municipality);

    @Query("SELECT e FROM Employee e WHERE e.branch.name=:branch")
    List<Employee> findByBranch(@Param("branch") String branch);

    @Query("SELECT e FROM Employee e WHERE e.arrears=:arrears")
    List<Employee> findByArrears(@Param("arrears") boolean arrears);

    @Query("SELECT e FROM Employee e WHERE e.email=:email AND e.user.login=:login")
    Optional<Employee> findByEmailAndLogin(@Param("email") String email, @Param("login") String login);

    @Query("UPDATE Employee e SET e.code =:oldCode WHERE e.code=:code AND e.user.login=:login")
    int updateByEmailAndLogin(@Param("oldCode") Long oldCode,@Param("code") Long code, @Param("login") String login);

    @Modifying
    @Query("UPDATE Employee e SET e.code = :code WHERE e.user.login = :login")
    int updateEmployeeCode(@Param("login") String login, @Param("code") Long code);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.user.login = :login")
    int deleteEmployee(@Param("login") String login);

}
