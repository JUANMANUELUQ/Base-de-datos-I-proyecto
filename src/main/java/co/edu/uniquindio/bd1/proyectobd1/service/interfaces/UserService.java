package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    User findByEmail(String email);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    boolean checkCredentials(String email, String password);
    List<User> findAll();
    void changePassword(Long id, String newPassword);
}
