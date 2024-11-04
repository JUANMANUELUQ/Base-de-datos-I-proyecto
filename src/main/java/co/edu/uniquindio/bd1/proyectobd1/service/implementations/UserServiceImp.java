package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.User;
import co.edu.uniquindio.bd1.proyectobd1.repository.UserRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepo;

    public UserServiceImp() {

    }

    public void save(User user) {
        userRepo.save(user);
    }

    public Optional<User> findByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}