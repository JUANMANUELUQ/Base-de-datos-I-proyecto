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
import java.util.Objects;

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

    private String usuarioSesion = "";

    //TODO Realizar estos tres metodos y devuelvan listas de DTO con los datos necesarios
    public List<EmployeeRegisterDTO> obtenerEmpleados() {
        List<EmployeeRegisterDTO> empleados = new ArrayList<EmployeeRegisterDTO>();
        List<Employee> empleadosObtenidos = employeeService.findAll();
        for (Employee empleado : empleadosObtenidos) {
            empleados.add(new EmployeeRegisterDTO(
                    empleado.getUser().getLogin(),
                    empleado.getUser().getPassword(),
                    empleado.getCode(),
                    empleado.getName(),
                    empleado.getEmail(),
                    empleado.getEmployeePosition().getName(),
                    empleado.getBranch().getMunicipality().getName(),
                    empleado.getBranch().getName()
            ));
        }
        return empleados;
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
        List<Municipality> municipios = municipalityService.findAllMunicipalities();
        List<String> nombresMunicipios = new ArrayList<String>();
        for (Municipality municipio : municipios) {
            nombresMunicipios.add(municipio.getName());
        }
        return nombresMunicipios;
    }

    public List<String> obtenerSucursales(String nombreMunicipio) {
        List<String> nombresSucursales = new ArrayList<String>();
        if (!nombreMunicipio.equals("")) {
            Municipality municipio = municipalityService.findByName(nombreMunicipio).get();
            List<Branch> sucursales = branchService.findByMunipality(municipio.getCode());

            for (Branch branch : sucursales) {
                nombresSucursales.add(branch.getName());
            }
        }
        return nombresSucursales;
    }

    public void guardarUsuario(EmployeeRegisterDTO empleado) throws Exception {
        if (userService.findByLogin(empleado.login()).isPresent()) {
            throw new Exception("Ya existe un usuario con ese nombre");
        }
        if (employeeService.findByCode(empleado.code()).isPresent()) {
            throw new Exception("Ya existe un empleado con ese codigo");
        }
        if (employeeService.findByEmail(empleado.email()).isPresent()) {
            throw new Exception("Ya existe un empleado con ese correo");
        }
        EmployeePosition cargoEmpleado = employeePositionService.findByName(empleado.position()).get();
        UserType tipoUsuario = userTypeService.findByEmployeePositionName(cargoEmpleado.getName()).get();
        Branch sucursal = branchService.finByName(empleado.branch()).get();
        User usuario = new User(empleado.login(), empleado.password(), LocalDate.now(), tipoUsuario);
        userService.save(usuario);
        Employee empleadoRegistrar = new Employee(empleado.name(), empleado.email(),
                false, usuario, sucursal, cargoEmpleado);
        empleadoRegistrar.setCode(empleado.code());
        employeeService.save(empleadoRegistrar);
    }

    public List<String> obtenerCargosEmpleados() {
        List<EmployeePosition> cargosEmpleados = employeePositionService.findAllEmployeePositions();
        List<String> cargosEmpleadosNombres = new ArrayList<String>();
        for (EmployeePosition cargo : cargosEmpleados) {
            cargosEmpleadosNombres.add(cargo.getName());
        }
        return cargosEmpleadosNombres;
    }

    public void actualizarUsuario(EmployeeUpdateDTO empleado) throws Exception {
        if (!empleado.oldLogin().equals(empleado.login()) && userService.findByLogin(empleado.login()).isPresent()) {
            throw new Exception("Ya existe un usuario con ese nombre");
        }
        if (!Objects.equals(empleado.oldCode(), empleado.code()) && employeeService.findByCode(empleado.code()).isPresent()) {
            throw new Exception("Ya existe un empleado con ese codigo");
        }
        if (!empleado.oldEmail().equals(empleado.email()) && employeeService.findByEmail(empleado.email()).isPresent()) {
            throw new Exception("Ya existe un empleado con ese correo");
        }
        EmployeePosition cargoEmpleado = employeePositionService.findByName(empleado.position()).get();
        UserType tipoUsuario = userTypeService.findByEmployeePositionName(cargoEmpleado.getName()).get();
        Branch sucursal = branchService.finByName(empleado.branch()).get();
        User usuario = userService.findByLogin(empleado.oldLogin()).get();
        Employee empleadoRegistrar = employeeService.findByCode(empleado.oldCode()).get();
        employeeService.deleteEmployee(empleadoRegistrar);
        System.out.println("0"+empleadoRegistrar.toString());
        userService.delete(usuario);
        usuario.setLogin(empleado.login());
        usuario.setPassword(empleado.password());
        usuario.setUserType(tipoUsuario);
        System.out.println("1"+usuario.toString());
        userService.save(usuario);
        System.out.println("2"+usuario.toString());
        System.out.println("3"+empleadoRegistrar.toString());
        empleadoRegistrar.setCode(empleado.code());
        empleadoRegistrar.setName(empleado.name());
        empleadoRegistrar.setEmail(empleado.email());
        empleadoRegistrar.setBranch(sucursal);
        empleadoRegistrar.setEmployeePosition(cargoEmpleado);
        empleadoRegistrar.setUser(usuario);
        System.out.println("4"+empleadoRegistrar.toString());
        employeeService.save(empleadoRegistrar);
    }

    public void borrarUsuario(EmployeeDeleteDTO empleado) {
        User usuario = userService.findByLogin(empleado.login()).get();
        Employee empleadoRegistrar = employeeService.findByCode(empleado.code()).get();
        employeeService.deleteEmployee(empleadoRegistrar);
        userService.delete(usuario);
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
     *
     * @return La instancia única de ModelFactoryController.
     */
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {

    }

    public void quemarDatos() {
        quemarDatosMunicipios();
        quemarSucursales();
        quemarCargoEmpleado();
        quemarTipoUsuario();
    }

    public void quemarDatosMunicipios() {
        Municipality municipio1 = new Municipality("Montenegro");
        municipio1.setCode((long) 1);
        Municipality municipio2 = new Municipality("Armenia");
        municipio2.setCode((long) 2);
        municipalityService.save(municipio1);
        municipalityService.save(municipio2);
    }

    public void quemarSucursales() {
        Municipality municipio1 = municipalityService.findByCode((long) 1).get();
        Municipality municipio2 = municipalityService.findByCode((long) 2).get();
        Branch sucursal1 = new Branch("Central", municipio1);
        sucursal1.setCodeBranch((long) 1);
        Branch sucursal2 = new Branch("La Perla", municipio1);
        sucursal2.setCodeBranch((long) 2);
        Branch sucursal3 = new Branch("El Sol", municipio2);
        sucursal3.setCodeBranch((long) 3);
        Branch sucursal4 = new Branch("Vista Alegre", municipio2);
        sucursal4.setCodeBranch((long) 4);
        branchService.save(sucursal1);
        branchService.save(sucursal2);
        branchService.save(sucursal3);
        branchService.save(sucursal4);
    }

    public void quemarCargoEmpleado() {
        EmployeePosition cargoEmpleado1 = new EmployeePosition("Administrador", 0, 15000000);
        cargoEmpleado1.setCode((long) 1);
        EmployeePosition cargoEmpleado2 = new EmployeePosition("Tesoreria", 0, 12000000);
        cargoEmpleado2.setCode((long) 2);
        EmployeePosition cargoEmpleado3 = new EmployeePosition("Operario", 0, 10000000);
        cargoEmpleado3.setCode((long) 3);
        EmployeePosition cargoEmpleado4 = new EmployeePosition("Ejecutivo", 0, 20000000);
        cargoEmpleado4.setCode((long) 4);
        EmployeePosition cargoEmpleado5 = new EmployeePosition("Otros", 0, 12000000);
        cargoEmpleado5.setCode((long) 5);
        employeePositionService.save(cargoEmpleado1);
        employeePositionService.save(cargoEmpleado2);
        employeePositionService.save(cargoEmpleado3);
        employeePositionService.save(cargoEmpleado4);
        employeePositionService.save(cargoEmpleado5);
    }

    public void quemarTipoUsuario() {
        UserType tipoUsuario1 = new UserType("Administrador");
        tipoUsuario1.setCode((long) 1);
        UserType tipoUsuario2 = new UserType("Param\u00E9trico");
        tipoUsuario2.setCode((long) 2);
        UserType tipoUsuario3 = new UserType("Espor\u00E1dicos");
        tipoUsuario3.setCode((long) 3);
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
