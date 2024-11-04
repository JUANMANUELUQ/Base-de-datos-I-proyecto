package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void save(User user);

    public Optional<User> findByLogin(String login);

    public void delete(User user);

}
