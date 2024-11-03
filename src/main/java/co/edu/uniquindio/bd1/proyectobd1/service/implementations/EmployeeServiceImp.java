package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Branch;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.Employee;
import co.edu.uniquindio.bd1.proyectobd1.repository.EmployeeRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public EmployeeServiceImp(){

    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepo.delete(employee);
    }

    @Override
    public List<Employee> findByBranch(Branch branch) {
        return List.of();
    }

    public List<Employee> findByBranch(String branch) {
        return employeeRepo.findByBranch(branch);
    }

    @Override
    public List<Employee> findDefaulters() {
        return List.of();
    }

    @Override
    public List<Employee> findByMunicipality(String municipality) {
        return employeeRepo.findByMunicipality(municipality);
    }

    public List<Employee> obtainEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> findByCode(Long code) {
        return employeeRepo.findByCode(code);
    }

    public void save(Employee employee) {
        employeeRepo.save(employee);
    }

    public Optional<Employee> findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    public List<Employee> findEmployeeArrears() {
        return employeeRepo.findByArrears(true);
    }
}