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

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public void deleteEmployee(Employee employee) {
        employeeRepo.delete(employee);
    }

    public List<Employee> findByBranch(String branch) {
        return employeeRepo.findByBranch(branch);
    }


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

    public Optional<Employee> findByEmailAndLogin(String email,String user) {
        return  employeeRepo.findByEmailAndLogin(email,user);
    }

    public void updateEmployeeCode(Long code,String user) {
        employeeRepo.updateEmployeeCode(user,code);
    }

    public void deleteEmployee(String user) {
        employeeRepo.deleteEmployee(user);
    }


    public Optional<Employee> findByUser(String user) {
        return employeeRepo.findByUser(user);
    }
}