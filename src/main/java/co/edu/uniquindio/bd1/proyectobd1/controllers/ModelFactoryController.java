package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.EmployeeDTO;
import co.edu.uniquindio.bd1.proyectobd1.model.MiEntidad;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.Employee;
import co.edu.uniquindio.bd1.proyectobd1.service.MiEntidadService;
import co.edu.uniquindio.bd1.proyectobd1.service.implementations.EmployeeServiceImp;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
public class ModelFactoryController {

    //Repositorio de prueba
    private MiEntidadService miEntidadService;

    private EmployeeServiceImp empleayeeService;

    private String usuarioSesion="";

    //TODO Realizar estos tres metodos y devuelvan listas de DTO con los datos necesarios
    public List<Object> obtenerEmpleados() {
        return new ArrayList<Object>();
    }

    public List<Object> obtenerPagosEmpleado() {
        return new ArrayList<Object>();
    }

    public List<Object> obtenerPagosTotales() {
        return new ArrayList<Object>();
    }

    public void registrarInicioSesion(String correo, LocalDate fecha) {

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

    public void organizarDatos() {
        if (estaBaseDatosIncompleta()) {
            quemarDatos();
        }
    }

    public boolean estaBaseDatosIncompleta() {
        boolean incompleta=false;
        List<Employee> l =empleayeeService.findAll();
        System.out.println(l.size()+" (ModelFactory)");
        if (empleayeeService.findAll().isEmpty()) {
            incompleta=true;
        }
        return incompleta;
    }

    public void quemarDatos() {

    }

    public void guardarEntidad(String nombre) {
        MiEntidad miEntidad = new MiEntidad();
        miEntidad.setNombre(nombre);
        miEntidadService.saveEntity(miEntidad);
    }

}
