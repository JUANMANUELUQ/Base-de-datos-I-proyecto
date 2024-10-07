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

    public List<String> obtenerMunicipios() {
        List<Municipality> municipios=municipalityService.findAllMunicipalities();
        List<String> nombresMunicipios=new ArrayList<String>();
        for (Municipality municipio : municipios) {
            nombresMunicipios.add(municipio.getName());
        }
        return nombresMunicipios;
    }

    public List<String> obtenerSucursales(String nombreMunicipio) {
        Municipality municipio=municipalityService.findByName(nombreMunicipio).get();
        List<Branch> sucursales=branchService.findByMunipality(municipio.getCode());
        List<String> nombresSucursales=new ArrayList<String>();
        for (Branch branch : sucursales) {
            nombresSucursales.add(branch.getName());
        }
        return nombresSucursales;
    }

    public void guardarUsuario(EmployeeRegisterDTO empleado) {

    }

    public List<String> obtenerCargosEmpleados() {
        List<EmployeePosition> cargosEmpleados=employeePositionService.findAllEmployeePositions();
        List<String> cargosEmpleadosNombres=new ArrayList<String>();
        for (EmployeePosition cargo : cargosEmpleados) {
            cargosEmpleadosNombres.add(cargo.getName());
        }
        return cargosEmpleadosNombres;
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
        quemarSucursales();
        quemarCargoEmpleado();
        quemarTipoUsuario();
    }

    public void quemarDatosMunicipios() {
        Municipality municipio1= new Municipality("Montenegro");
        municipio1.setCode((long)1);
        Municipality municipio2= new Municipality("Armenia");
        municipio2.setCode((long)2);
        municipalityService.save(municipio1);
        municipalityService.save(municipio2);
    }

    public void quemarSucursales() {
        Municipality municipio1=municipalityService.findByCode((long)1).get();
        Municipality municipio2=municipalityService.findByCode((long)2).get();
        Branch sucursal1=new Branch("Central",municipio1);
        sucursal1.setCodeBranch((long)1);
        Branch sucursal2=new Branch("La Perla",municipio1);
        sucursal2.setCodeBranch((long)2);
        Branch sucursal3=new Branch("El Sol",municipio2);
        sucursal3.setCodeBranch((long)3);
        Branch sucursal4=new Branch("Vista Alegre",municipio2);
        sucursal4.setCodeBranch((long)4);
        branchService.save(sucursal1);
        branchService.save(sucursal2);
        branchService.save(sucursal3);
        branchService.save(sucursal4);
    }

    public void quemarCargoEmpleado() {
        EmployeePosition cargoEmpleado1=new EmployeePosition("Administrador",0,15000000);
        cargoEmpleado1.setCode((long)1);
        EmployeePosition cargoEmpleado2=new EmployeePosition("Tesoreria",0,12000000);
        cargoEmpleado2.setCode((long)2);
        EmployeePosition cargoEmpleado3=new EmployeePosition("Operario",0,10000000);
        cargoEmpleado3.setCode((long)3);
        EmployeePosition cargoEmpleado4=new EmployeePosition("Ejecutivo",0,20000000);
        cargoEmpleado4.setCode((long)4);
        EmployeePosition cargoEmpleado5=new EmployeePosition("Otros",0,12000000);
        cargoEmpleado5.setCode((long)5);
        employeePositionService.save(cargoEmpleado1);
        employeePositionService.save(cargoEmpleado2);
        employeePositionService.save(cargoEmpleado3);
        employeePositionService.save(cargoEmpleado4);
        employeePositionService.save(cargoEmpleado5);
    }

    public void quemarTipoUsuario() {
        UserType tipoUsuario1=new UserType("Administrador");
        tipoUsuario1.setCode((long)1);
        UserType tipoUsuario2=new UserType("Param\u00E9trico");
        tipoUsuario2.setCode((long)2);
        UserType tipoUsuario3=new UserType("Espor\u00E1dicos");
        tipoUsuario3.setCode((long)3);
        userTypeService.save(tipoUsuario1);
        userTypeService.save(tipoUsuario2);
        userTypeService.save(tipoUsuario3);
    }

    public void guardarEntidad(String nombre) {
        MiEntidad miEntidad = new MiEntidad();
        miEntidad.setNombre(nombre);
        miEntidadService.saveEntity(miEntidad);
    }

}
