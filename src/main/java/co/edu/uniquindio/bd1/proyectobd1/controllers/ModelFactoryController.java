package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.*;
import co.edu.uniquindio.bd1.proyectobd1.model.MiEntidad;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.*;
import co.edu.uniquindio.bd1.proyectobd1.service.MiEntidadService;
import co.edu.uniquindio.bd1.proyectobd1.service.implementations.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ModelFactoryController {

    //Repositorio de prueba
    private MiEntidadService miEntidadService;

    private AuditServiceImp auditService;
    private BranchServiceImp branchService;
    private EmployeePositionServiceImp employeePositionService;
    private EmployeeServiceImp employeeService;
    private MunicipalityServiceImp municipalityService;
    private UserServiceImp userService;
    private UserTypeServiceImp userTypeService;

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

    public boolean estaBaseDatosIncompleta() {
        boolean incompleta=false;
        List<Employee> l =employeeService.findAll();
        System.out.println(l.size()+" (ModelFactory)");
        if (employeeService.findAll().isEmpty()) {
            incompleta=true;
        }
        return incompleta;
    }

    public void quemarDatos() {
        quemarDatosMunicipios();
    }

    public void quemarDatosMunicipios() {
        Municipality municipio1= new Municipality("Montenegro");
        municipio1.setCode((long)1);
        Municipality municipio2= new Municipality("Armenia");
        municipio2.setCode((long)2);
        municipalityService.save(municipio1);
        municipalityService.save(municipio2);
    }

    public void guardarEntidad(String nombre) {
        MiEntidad miEntidad = new MiEntidad();
        miEntidad.setNombre(nombre);
        miEntidadService.saveEntity(miEntidad);
    }

}
