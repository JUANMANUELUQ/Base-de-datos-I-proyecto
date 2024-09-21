package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.model.MiEntidad;
import co.edu.uniquindio.bd1.proyectobd1.service.MiEntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

public class ModelFactoryController {

    private MiEntidadService miEntidadService;

    public MiEntidadService getMiEntidadService() {
        return miEntidadService;
    }

    public void setMiEntidadService(MiEntidadService miEntidadService) {
        this.miEntidadService = miEntidadService;
    }

    /**
     * Clase que implementa el patrón Singleton para controlar la creación de
     * instancias de ModelFactoryController.
     */
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aqu� al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    /**
     * Método estático para obtener la única instancia de ModelFactoryController.
     * @return La instancia única de ModelFactoryController.
     */
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {

    }

    public void guardarEntidad(String nombre) {
        MiEntidad miEntidad = new MiEntidad();
        miEntidad.setNombre(nombre);
        miEntidadService.saveEntity(miEntidad);
    }

}
