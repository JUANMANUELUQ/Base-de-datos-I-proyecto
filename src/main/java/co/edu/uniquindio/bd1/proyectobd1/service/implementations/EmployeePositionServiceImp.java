package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.repository.EmployeeRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.EmployeePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeePositionServiceImp implements EmployeePositionService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public EmployeePositionServiceImp() {

    }

}
