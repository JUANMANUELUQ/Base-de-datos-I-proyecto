package co.edu.uniquindio.bd1.proyectobd1;


import co.edu.uniquindio.bd1.proyectobd1.application.pruebaMain;
import co.edu.uniquindio.bd1.proyectobd1.controllers.ModelFactoryController;
import co.edu.uniquindio.bd1.proyectobd1.service.MiEntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication(proxyBeanMethods = false)
public class MainPrueba implements CommandLineRunner {

    @Autowired
    private MiEntidadService miEntidadService;

    public static void main(String[] args) {
        SpringApplication.run(MainPrueba.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ModelFactoryController mfm=ModelFactoryController.getInstance();
        mfm.setMiEntidadService(miEntidadService);
        Application.launch(pruebaMain.class, args);
    }
}
