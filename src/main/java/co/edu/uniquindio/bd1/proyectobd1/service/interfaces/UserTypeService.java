package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.UserType;

import java.util.Optional;

public interface UserTypeService {

    public void save(UserType userType);

    public Optional<UserType> findByEmployeePositionName(String name);

    public Optional<UserType> findByLevel(String level);

}
