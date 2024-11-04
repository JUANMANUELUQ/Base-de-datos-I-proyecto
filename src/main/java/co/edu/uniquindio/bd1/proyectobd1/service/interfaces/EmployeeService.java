package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Branch;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.Employee;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> findAll();

    public void deleteEmployee(Employee employee);

    public List<Employee> findByBranch(String branch);


    public List<Employee> findByMunicipality(String municipality);

    public List<Employee> obtainEmployees();

    public Optional<Employee> findByCode(Long code);

    public void save(Employee employee);

    public Optional<Employee> findByEmail(String email);

    public List<Employee> findEmployeeArrears();

}
