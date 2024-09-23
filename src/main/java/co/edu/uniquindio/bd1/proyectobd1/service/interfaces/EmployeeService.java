package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Branch;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.Employee;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.User;

import java.util.List;

public interface EmployeeService {
    Employee findById(Long id);
    List<Employee> findAll();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    List<Employee> findByBranch(Branch branch);
    List<Employee> findDefaulters();//Esto retornará todos los morosos
    List<Employee> findByMunicipality(String municipality);
}
