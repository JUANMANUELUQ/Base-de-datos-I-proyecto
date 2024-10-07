package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.UserType;
import co.edu.uniquindio.bd1.proyectobd1.repository.UserTypeRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTypeServiceImp implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepo;

    public UserTypeServiceImp() {

    }


    public void save(UserType userType) {
        userTypeRepo.save(userType);
    }
}
