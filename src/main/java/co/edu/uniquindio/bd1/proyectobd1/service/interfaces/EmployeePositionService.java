package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.EmployeePosition;

import java.util.List;
import java.util.Optional;

public interface EmployeePositionService {

    public List<EmployeePosition> findAllEmployeePositions();

    public Optional<EmployeePosition> findByName(String name);

}
