package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.EmployeePosition;
import co.edu.uniquindio.bd1.proyectobd1.repository.EmployeePositionRepository;
import co.edu.uniquindio.bd1.proyectobd1.repository.EmployeeRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.EmployeePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeePositionServiceImp implements EmployeePositionService {

    @Autowired
    private EmployeePositionRepository employeePositionRepo;

    public EmployeePositionServiceImp() {

    }

    public void save(EmployeePosition employeePosition) {
        employeePositionRepo.save(employeePosition);
    }

    public List<EmployeePosition> findAllEmployeePositions() {
        return employeePositionRepo.findAll();
    }
}
