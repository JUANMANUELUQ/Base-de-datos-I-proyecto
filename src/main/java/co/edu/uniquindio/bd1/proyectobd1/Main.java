package co.edu.uniquindio.bd1.proyectobd1;

import co.edu.uniquindio.bd1.proyectobd1.application.App;
import co.edu.uniquindio.bd1.proyectobd1.controllers.ModelFactoryController;
import co.edu.uniquindio.bd1.proyectobd1.service.implementations.EmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication(proxyBeanMethods = false)
public class Main implements CommandLineRunner {

    @Autowired
    private EmployeeServiceImp empleayeeService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ModelFactoryController mfm=ModelFactoryController.getInstance();
        mfm.setEmpleayeeService(empleayeeService);
        mfm.organizarDatos();
        Application.launch(App.class, args);
    }

}
