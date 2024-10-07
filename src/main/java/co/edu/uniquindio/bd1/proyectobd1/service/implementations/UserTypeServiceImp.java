package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.UserType;
import co.edu.uniquindio.bd1.proyectobd1.repository.UserTypeRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTypeServiceImp implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepo;

    public UserTypeServiceImp() {

    }


    public void save(UserType userType) {
        userTypeRepo.save(userType);
    }

    public Optional<UserType> findByEmployeePositionName(String name) {
        Optional<UserType> userType=Optional.empty();
        switch (name) {
            case "Administrador": userType=userTypeRepo.findByLevel("Administrador"); break;
            case "Tesoreria": userType=userTypeRepo.findByLevel("Param\u00E9trico"); break;
            case "Operario":
            case "Ejecutivo":
            case "Otros": userType=userTypeRepo.findByLevel("Espor\u00E1dicos"); break;
        }
        return userType;
    }
}
