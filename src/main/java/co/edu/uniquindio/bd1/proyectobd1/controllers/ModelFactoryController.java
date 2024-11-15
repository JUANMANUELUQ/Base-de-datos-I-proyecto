package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.*;
import co.edu.uniquindio.bd1.proyectobd1.model.entities.*;
import co.edu.uniquindio.bd1.proyectobd1.service.implementations.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Getter
@Setter
public class ModelFactoryController {

    //Repositorio de prueba

    private AuditServiceImp auditService;
    private BranchServiceImp branchService;
    private EmployeePositionServiceImp employeePositionService;
    private EmployeeServiceImp employeeService;
    private MunicipalityServiceImp municipalityService;
    private UserServiceImp userService;
    private UserTypeServiceImp userTypeService;
    private LoanServiceImp loanService;
    private LoanRequestServiceImp loanRequestService;
    private LoanRequestStatusServiceImp loanRequestStatusService;
    private PeriodServiceImp periodService;
    private LoanPaymentServiceImp loanPaymentService;

    private Employee empleadoSesion = null;
    private Audit auditoriaEmpleado = null;

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

    public List<LoanDTO> obtenerPagosEmpleadoSesion() {
        List<Loan> prestamos=loanService.findByEmployee(this.empleadoSesion.getCode());
        List<LoanDTO> prestamosInfo=new ArrayList<LoanDTO>();
        for (Loan loan : prestamos) {
            prestamosInfo.add(new LoanDTO(
                    loan.getCode(),
                    loan.getCreationDate(),
                    loan.getAmount(),
                    loan.getPeriod().getPeriodMonths(),
                    loan.getPeriod().getInterestRate(),
                    this.empleadoSesion.getCode()
            ));
        }
        return prestamosInfo;
    }

    public List<LoanDTO> obtenerPagosTotales() {
        List<Loan> prestamos=loanService.findAll();
        List<LoanDTO> prestamosInfo=new ArrayList<LoanDTO>();
        for (Loan loan : prestamos) {
            prestamosInfo.add(new LoanDTO(
                    loan.getCode(),
                    loan.getCreationDate(),
                    loan.getAmount(),
                    loan.getPeriod().getPeriodMonths(),
                    loan.getPeriod().getInterestRate(),
                    loan.getEmployee().getCode()
            ));
        }
        return prestamosInfo;
    }

    public List<MunicipalityInfoDTO> obtenerMunicipios() {
        List<Municipality> municipios = municipalityService.findAllMunicipalities();
        List<MunicipalityInfoDTO> nombresMunicipios = new ArrayList<MunicipalityInfoDTO>();
        for (Municipality municipio : municipios) {
            nombresMunicipios.add(new MunicipalityInfoDTO(
                    municipio.getName(),
                    municipio.getPopulation(),
                    municipio.getDescription()
            ));
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
        Branch sucursal = branchService.findByName(empleado.branch()).get();
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
        Branch sucursal = branchService.findByName(empleado.branch()).get();
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

    public String iniciarSesion(String correo, String contrasenia) {
        Optional<Employee> empleado=employeeService.findByEmail(correo);
        User usuario;
        if (empleado.isEmpty()) {
            return "";
        }
        usuario=empleado.get().getUser();
        if (usuario.getPassword().equals(contrasenia)) {
            setEmpleadoSesion(empleado.get());
            generarAuditoriaEntrada();
            return usuario.getUserType().getLevel();
        } else {
            return "";
        }
    }

    public void generarAuditoriaEntrada() {
        Audit auditoria = new Audit();
        auditoria.setLogin(empleadoSesion.getUser());
        LocalDateTime now = LocalDateTime.now();
        auditoria.setEntryDate(now.toLocalDate());
        auditoria.setEntryTime(now.toLocalTime());
        this.auditoriaEmpleado = auditoria;
        System.out.println(auditoriaEmpleado);
    }

    public void generarAuditoriaSalida() {
        if (this.auditoriaEmpleado != null) {
            auditoriaEmpleado.setOutputDate(LocalDate.now());
            auditoriaEmpleado.setOutputTime(LocalTime.now());
            auditService.save(auditoriaEmpleado);
        }
    }

    public void cerrarSesion() {
        generarAuditoriaSalida();
        setEmpleadoSesion(null);
        setAuditoriaEmpleado(null);
    }

    public List<Integer> obtenerPeriodosNombres() {
        List<Period> periodos=periodService.findAll();
        List<Integer> periodosMeses=new ArrayList<>();
        for (Period periodo : periodos) {
            periodosMeses.add(periodo.getPeriodMonths());
        }
        return periodosMeses;
    }

    public void crearSolicitudPrestamo(CreateLoanRequestDTO createLoanRequest) {
        Period periodo=periodService.findByPeriodMonths(createLoanRequest.period()).get();
        LoanRequestStatus estadoPrestamo=loanRequestStatusService.findByName("pendiente").get();
        LoanRequest loanRequest=new LoanRequest();
        loanRequest.setEmployee(this.empleadoSesion);
        loanRequest.setPeriod(periodo);
        loanRequest.setLoanStatus(estadoPrestamo);
        loanRequest.setRequestDate(LocalDate.now());
        loanRequest.setRequestedAmount(createLoanRequest.amount());
        loanRequestService.save(loanRequest);
    }

    public List<LoanRequestInfoDTO> obtenerSolicitudesPrestamo() {
        List<LoanRequest> solicitudesPrestamo=loanRequestService.findAll();
        List<LoanRequestInfoDTO> solicitudesPrestamoInfo=new ArrayList<>();
        for (LoanRequest loanRequest : solicitudesPrestamo) {
            solicitudesPrestamoInfo.add(new LoanRequestInfoDTO(
                    loanRequest.getLoanNumber(),
                    loanRequest.getEmployee().getEmail(),
                    loanRequest.getRequestDate(),
                    loanRequest.getRequestedAmount(),
                    loanRequest.getPeriod().getPeriodMonths(),
                    loanRequest.getLoanStatus().getName()
            ));
        }
        return solicitudesPrestamoInfo;
    }

    public void cambiarEstadoSolicitud(ChangeLoanRequestStateDTO changeLoanRequestStateDTO) {
        LoanRequest solicitudPrestamo=loanRequestService.findByLoanNumber(changeLoanRequestStateDTO.loanNumber()).get();
        LoanRequestStatus estadoPrestamo=loanRequestStatusService.findByName(changeLoanRequestStateDTO.state()).get();
        solicitudPrestamo.setLoanStatus(estadoPrestamo);
        loanRequestService.save(solicitudPrestamo);
        if (changeLoanRequestStateDTO.state().equals("aprobada")) {
            Loan prestamo=new Loan(
                    solicitudPrestamo.getRequestedAmount(),
                    LocalDate.now(),
                    solicitudPrestamo,
                    solicitudPrestamo.getPeriod(),
                    solicitudPrestamo.getEmployee()
            );
            loanService.save(prestamo);
        }
    }

    public EmployeeDTO obtenerEmpleadoCodigo(Long codigo) {
        Employee empleado=employeeService.findByCode(codigo).get();
        return new EmployeeDTO(
                empleado.getUser().getLogin(),
                empleado.getUser().getCreationDate(),
                empleado.getName(),
                empleado.getEmail(),
                empleado.isArrears(),
                empleado.getEmployeePosition().getName(),
                empleado.getEmployeePosition().getSalary(),
                empleado.getBranch().getName(),
                empleado.getBranch().getMunicipality().getName()
        );
    }

    public EmployeeDTO obtenerEmpleadoCorreo(String correo) {
        Employee empleado=employeeService.findByEmail(correo).get();
        return new EmployeeDTO(
                empleado.getUser().getLogin(),
                empleado.getUser().getCreationDate(),
                empleado.getName(),
                empleado.getEmail(),
                empleado.isArrears(),
                empleado.getEmployeePosition().getName(),
                empleado.getEmployeePosition().getSalary(),
                empleado.getBranch().getName(),
                empleado.getBranch().getMunicipality().getName()
        );
    }

    public float obtenerTopeEmpleadoSesion() {
        return this.empleadoSesion.getEmployeePosition().getCap();
    }

    public void registrarPago(CreateLoanPayment createPayment) {
        Loan prestamo=loanService.findByCode(createPayment.loanNumber()).get();
        LoanPayment pago=new LoanPayment(
                createPayment.paymentNumber(),
                createPayment.paymentDate(),
                createPayment.value(),
                prestamo
        );
        loanPaymentService.save(pago);
        List<LoanPayment> pagosPrestamos=loanPaymentService.findByLoanCode(prestamo.getCode());
        boolean mora=estaEnMora(prestamo,pagosPrestamos);
        if (mora && !this.empleadoSesion.isArrears()) {
            this.empleadoSesion.setArrears(true);
            employeeService.save(this.empleadoSesion);
        }
    }

    public boolean estaEnMora(Loan prestamo,List<LoanPayment> pagosPrestamos) {
        boolean estaEnMora=true;
        int pagos=pagosPrestamos.size();
        LocalDate fechaInicioPlazo=prestamo.getCreationDate().plusMonths(pagos-1);
        LocalDate siguienteMes=fechaInicioPlazo.plusMonths(1);
        LocalDate fechaFinalPlazo=LocalDate.of(siguienteMes.getYear(),siguienteMes.getMonth(),10);
        if (fechaFinalPlazo.plusDays(1).isBefore(LocalDate.now())) {
            estaEnMora=false;
        }
        return estaEnMora;
    }



    public List<PaymentDTO> obtenerPrestamosEmpleado(SearchPaymentDTO datosPago) {
        List<LoanPayment> pagosPrestamos=
                loanPaymentService.findByEmployeeAndLoan(datosPago.codeEmployee(),datosPago.codeLoan());
        List<PaymentDTO> pagosPrestamosInfo=new ArrayList<>();
        for (LoanPayment pago : pagosPrestamos) {
            pagosPrestamosInfo.add(new PaymentDTO(
                    pago.getPaymentNumber(),
                    pago.getPaymentDate(),
                    pago.getValue()
            ));
        }
        return pagosPrestamosInfo;
    }

    public boolean existePrestamoUsuarioSesion(Long numeroPrestmo) {
        Optional<Loan> prestamo=loanService.findByCode(numeroPrestmo);
        System.out.println(prestamo);
        if (prestamo.isPresent() &&
                prestamo.get().getEmployee().getCode().equals(this.empleadoSesion.getCode())) {
            return true;
        }
        return false;
    }

    public boolean existeOtroMunicipio(String municipioBuscar,String municipioActual) {
        Optional<Municipality> municipio =municipalityService.findByName(municipioBuscar);
        if (municipio.isEmpty() || municipio.get().getName().equals(municipioActual)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existeSucursalEnMunicipio(MunicipalityBranchDTO municipalityBranchDTO) {
        List<Branch> sucursales=branchService.findByMunipalityName(municipalityBranchDTO.municipality());
        for (Branch branch : sucursales) {
            if (branch.getName().equals(municipalityBranchDTO.municipality())) {
                return true;
            }
        }
        return false;
    }

    public void guardarMunicipio(MunicipalityInfoDTO municipioDatos) {
        Municipality municipioGuardar=new Municipality(municipioDatos.name(),municipioDatos.population(),
                municipioDatos.description());
        municipalityService.save(municipioGuardar);
    }

    public void editarMunicipio(MunicipalityEditDTO datosMunicipio) {
        Municipality municipio=municipalityService.findByName(datosMunicipio.oldMunicipality()).get();
        municipio.setName(datosMunicipio.newMunicipality());
        municipio.setPopulation(datosMunicipio.population());
        municipio.setDescription(datosMunicipio.description());
        municipalityService.save(municipio);
    }

    public void eliminarMunicipio(String prestamo) {
        Municipality municipio=municipalityService.findByName(prestamo).get();
        municipalityService.delete(municipio);
    }

    public void guardarSucursal(MunicipalityBranchDTO municipioSucursal) {
        Municipality municipio=municipalityService.findByName(municipioSucursal.municipality()).get();
        Branch sucursal=new Branch(municipioSucursal.branch(),municipio);
        branchService.save(sucursal);
    }

    public void editarSucursal(BranchEditDTO datos) {
        Branch sucursal=branchService.findByMunipalityNameAndMunicipality(
                datos.oldBranch(),datos.municipality()).get();
        sucursal.setName(datos.newBranch());
        branchService.save(sucursal);
    }

    public void eliminarSucursal(MunicipalityBranchDTO datos) {
        Branch sucursal=branchService.findByMunipalityNameAndMunicipality(
                datos.branch(),datos.municipality()).get();
        branchService.delete(sucursal);
    }

    public List<ReportMunicipalityTotalDTO> obtenerDatosTotalPrestamoMunicipios() {
        List<Municipality> municipios=municipalityService.findAllMunicipalities();
        List<ReportMunicipalityTotalDTO> datosReporte=new ArrayList<>();
        for (Municipality municipal : municipios) {
            List<Loan> prestamos=loanService.findByMunicipality(municipal.getName());
            datosReporte.add(new ReportMunicipalityTotalDTO(
                    municipal.getName(),
                    calcularTotalPrestamos(prestamos),
                    prestamos.size(),
                    employeeService.findByMunicipality(municipal.getName()).size()
            ));
        }
        return datosReporte;
    }

    public float calcularTotalPrestamos(List<Loan> prestamos) {
        float total=0;
        for (Loan loan : prestamos) {
            total+=loan.getAmount();
        }
        return total;
    }

    public List<ReportBranchTotalDTO> obtenerDatosTotalPrestamoSucursales(String municipio) {
        List<Branch> sucursales=branchService.findByMunipality(municipio);
        List<ReportBranchTotalDTO> datosReporte=new ArrayList<>();
        for (Branch sucursal : sucursales) {
            List<Loan> prestamos=loanService.findByBranch(sucursal.getName());
            datosReporte.add(new ReportBranchTotalDTO(
                    sucursal.getName(),
                    calcularTotalPrestamos(prestamos),
                    prestamos.size(),
                    employeeService.findByBranch(sucursal.getName()).size()
            ));
        }
        return datosReporte;
    }

    public List<EmployeeTotalDTO> obtenerDatosTotalEmpleados() {
        List<Employee> employees=employeeService.findAll();
        List<EmployeeTotalDTO> datosReporte=new ArrayList<>();
        for (Employee employee : employees) {
            List<Loan> prestamos=loanService.findByEmployee(employee.getCode());
            datosReporte.add(new EmployeeTotalDTO(
                    employee.getCode(),
                    employee.getName(),
                    employee.getEmail(),
                    calcularTotalPrestamos(prestamos),
                    prestamos.size()
            ));
        }
        return datosReporte;
    }

    public List<ReportEmployeeArrearstDTO> obtenerDatosEmpleadosMororsos() {
        List<Employee> empleadosMorosos=employeeService.findEmployeeArrears();
        List<ReportEmployeeArrearstDTO> datosReporte=new ArrayList<>();
        for (Employee empleado : empleadosMorosos) {
            datosReporte.add(new ReportEmployeeArrearstDTO(
                    empleado.getCode(),
                    empleado.getName(),
                    empleado.getEmail(),
                    calcularDeuda(empleado)
            ));
        }
        return datosReporte;
    }

    public float calcularDeuda(Employee empleado) {
        float deuda=0;
        List<Loan> prestamos=loanService.findByEmployee(empleado.getCode());
        for (Loan prestamo : prestamos) {
            List<LoanPayment> pagos=loanPaymentService.findByLoanCode(prestamo.getCode());
            if (estaEnMora(prestamo,pagos)) {
                int cantidadPrestamosDeduda=obtenerPrestamosRestantes(prestamo,pagos);
                deuda+=cantidadPrestamosDeduda+(prestamo.getAmount()
                        * (1+prestamo.getPeriod().getInterestRate()));
            }
        }
        return deuda;
    }

    public int obtenerPrestamosRestantes(Loan prestamo,List<LoanPayment> pagosPrestamos) {
        int pagos=pagosPrestamos.size();
        LocalDate fechaInicioPlazo=prestamo.getCreationDate().plusMonths(pagos-1);
        LocalDate siguienteMes=fechaInicioPlazo.plusMonths(1);
        LocalDate fechaFinalPlazo=LocalDate.of(siguienteMes.getYear(),siguienteMes.getMonth(),10);
        fechaFinalPlazo=fechaFinalPlazo.plusDays(1);
        LocalDate fechaActual=LocalDate.now();
        int cont=0;
        while (!fechaFinalPlazo.isBefore(fechaActual)) {
            cont++;
            fechaActual.plusMonths(-1);
        }
        if (pagos<cont) {
            return prestamo.getPeriod().getPeriodMonths()-pagos;
        } else {
            return cont;
        }
    }

    /*
    public boolean estaEnMora(Loan prestamo,List<LoanPayment> pagosPrestamos) {
        boolean estaEnMora=true;
        int pagos=pagosPrestamos.size();
        LocalDate fechaInicioPlazo=prestamo.getCreationDate().plusMonths(pagos-1);
        LocalDate siguienteMes=fechaInicioPlazo.plusMonths(1);
        LocalDate fechaFinalPlazo=LocalDate.of(siguienteMes.getYear(),siguienteMes.getMonth(),10);
        if (fechaFinalPlazo.plusDays(1).isBefore(LocalDate.now())) {
            estaEnMora=false;
        }
        return estaEnMora;
    }
     */

    public List<ReportLoanRequestDTO> obtenerDatosSolicitudesRealizadas() {
        List<LoanRequest> solicitudPrestamo=loanRequestService.findByEmployee(this.empleadoSesion.getCode());
        List<ReportLoanRequestDTO> datosReporte=new ArrayList<>();
        for (LoanRequest solicitud : solicitudPrestamo) {
            datosReporte.add(new ReportLoanRequestDTO(
                    solicitud.getRequestDate(),
                    solicitud.getLoanStatus().getName(),
                    solicitud.getRequestedAmount()
            ));
        }
        return datosReporte;
    }

    public List<ReportCalculatedPaymentsDTO> obtenerDatosPagosCalculados(Long numeroPrestamo) {
        Loan prestamo=loanService.findByCode(numeroPrestamo).get();
        List<ReportCalculatedPaymentsDTO> datosReporte=new ArrayList<>();
        float valorIntereses=prestamo.getAmount()*(1+prestamo.getPeriod().getInterestRate());
        int periodosMeses=prestamo.getPeriod().getPeriodMonths();
        for (int i=1;i<=periodosMeses;i++) {
            datosReporte.add(new ReportCalculatedPaymentsDTO(
                    i,
                    valorIntereses/periodosMeses
            ));
        }
        return datosReporte;
    }

    public List<ReportCalculatedPaymentsDTO> obtenerDatosPagosCalculados(CreateLoanRequestDTO prestamo) {
        Period periodo=periodService.findByPeriodMonths(prestamo.period()).get();
        List<ReportCalculatedPaymentsDTO> datosReporte=new ArrayList<>();
        float valorIntereses=prestamo.amount()*(1+periodo.getInterestRate());
        int periodosMeses=prestamo.period();
        for (int i=1;i<=periodosMeses;i++) {
            datosReporte.add(new ReportCalculatedPaymentsDTO(
                    i,
                    valorIntereses/periodosMeses
            ));
        }
        return datosReporte;
    }

    public Map<String,String> obtenerAyudasUsuarioSesion() {
        Map<String,String> ayudas=new HashMap<>();
        switch (this.empleadoSesion.getUser().getUserType().getLevel()) {
            case "Administrador": ayudas=obtenerAyudasAdministrador(); break;
            case "Param\u00E9trico": ayudas=obtenerAyudasTesoreria(); break;
            case "Espor\u00E1dicos": ayudas=obtenerAyudasEmpleado(); break;
        }
        return ayudas;
    }

    public Map<String,String> obtenerAyudasAdministrador() {
        Map<String,String> ayudas=new HashMap<>();
        ayudas.put("Municipio","Se puede registrar, ver ,editar, eliminar los distintos datos de los " +
                "municipios");
        ayudas.put("Sucursal","Se puede registrar, ver ,editar, eliminar los distintos datos de las " +
                "sucursales de cada municipio, eligiendo el municipio a la que pertenecen");
        ayudas.put("Gestion empleado","Se puede registrar, ver ,editar, eliminar los distintos datos " +
                "de los empleados registrados");
        ayudas.put("Solicitud de prestamo","Permite realizar una solicitud de prestamos indicando el " +
                "monto a solicitar, junto de en cuantas cuotas se desea pagar");
        ayudas.put("Pago cuotas","Se ingresa despues de realizar el pago de una cuota en el banco " +
                "indicando el numero de prestamo de la cuota que se esta pagando, el  numero de " +
                "cuota la fecha donde se realizo el pago y el valor de la cuota");
        ayudas.put("Reportes","Permite realizar reportes en archivos pdf, en el directorio indicado. " +
                "Se puede realizar los reportes del total del dinero prestado en todo los municipios y " +
                "el  de todas las sucursales del municipio seleccionado");
        ayudas.put("Pagos realizados","Permite ver los prestamos realizados con la cuenta de la sesion " +
                "junto con sus respectivos pagos al momento de seleccionar un prestamo");
        ayudas.put("Calculadora","Permite hacer operaciones matematicas simples");
        ayudas.put("Calendario","Permite ver un calendario del mes actual, aunque tambien permite " +
                "los demas meses y años");
        return ayudas;
    }

    public Map<String,String> obtenerAyudasTesoreria() {
        Map<String,String> ayudas=new HashMap<>();
        ayudas.put("Solicitudes de prestamos","Se pueden ver y poner en estudio, aprobar o rechazar " +
                "las solicitudes de prestamos realizadas por los empleados, tambien se puede ver " +
                "la informacion del empleado que realizo la solicitud seleecionada");
        ayudas.put("Reportes","Permite realizar reportes en archivos pdf, en el directorio indicado. " +
                "Se pueden realizar los reportes de los empleados morosos y el del total prestado a " +
                "los todos los empleados");
        ayudas.put("Pagos totales","Se pueden ver todos los prestamos realizados por todos lo empleados " +
                "junto con sus pagos realizados y la respectiva informacion del empleado que pidio " +
                "el prestamo");
        ayudas.put("Calculadora","Permite hacer operaciones matematicas simples");
        ayudas.put("Calendario","Permite ver un calendario del mes actual, aunque tambien permite " +
                "los demas meses y años");
        return ayudas;
    }

    public Map<String,String> obtenerAyudasEmpleado() {
        Map<String,String> ayudas=new HashMap<>();
        ayudas.put("Solicitud de prestamo","Permite realizar una solicitud de prestamos indicando el " +
                "monto a solicitar, junto de en cuantas cuotas se desea pagar");
        ayudas.put("Pago cuotas","Se ingresa despues de realizar el pago de una cuota en el banco " +
                "indicando el numero de prestamo de la cuota que se esta pagando, el  numero de " +
                "cuota la fecha donde se realizo el pago y el valor de la cuota");
        ayudas.put("Reportes","Permite realizar reportes en archivos pdf, en el directorio indicado. " +
                "Se pueden realizar reportes de como deben ser el pago de las cuotas de los prestamos " +
                "y el de todas las solicitudes realizadas por el usuario");
        ayudas.put("Pagos realizados","Permite ver los prestamos realizados con la cuenta de la sesion " +
                "junto con sus respectivos pagos al momento de seleccionar un prestamo");
        ayudas.put("Calculadora","Permite hacer operaciones matematicas simples");
        ayudas.put("Calendario","Permite ver un calendario del mes actual, aunque tambien permite " +
                "los demas meses y años");
        return ayudas;
    }

    public List<AuditInfoDTO> obtenerAuditoriasInfo() {

        List<Audit> auditorias=auditService.findAll();
        List<AuditInfoDTO> auditoriasInfo=new ArrayList<>();
        for (Audit auditoria: auditorias) {
            auditoriasInfo.add(new AuditInfoDTO(
                    auditoria.getEntryDate(),
                    auditoria.getEntryTime(),
                    auditoria.getOutputDate(),
                    auditoria.getOutputTime(),
                    auditoria.getLogin().getLogin()
            ));
        }
        return auditoriasInfo;
    }

    public EmployeeDTO obtenerEmpleadoUsuario(String usuario) {
        Employee empleado=employeeService.findByUser(usuario).get();
        return new EmployeeDTO(
                empleado.getUser().getLogin(),
                empleado.getUser().getCreationDate(),
                empleado.getName(),
                empleado.getEmail(),
                empleado.isArrears(),
                empleado.getEmployeePosition().getName(),
                empleado.getEmployeePosition().getSalary(),
                empleado.getBranch().getName(),
                empleado.getBranch().getMunicipality().getName()
        );
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
        quemarTiposUsuario();
        quemarUsuariosEmpleados();
        quemarPeriodos();
        quemarEstadosSolicitud();
    }

    public void quemarDatosMunicipios() {
        Municipality municipio1 = new Municipality("Montenegro",41700L,
                "Es un pequeño y encantador municipio famoso por su conexión con " +
                        "la cultura cafetera");
        municipio1.setCode((long) 1);
        Municipality municipio2 = new Municipality("Armenia",310000L,
                "La capital del departamento del Quindio, es conocida como \"La " +
                        "Ciudad Milagro\" y es un centro economico y cultural");
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
        EmployeePosition cargoEmpleado1 = new EmployeePosition("Administrador", 3500000, 15000000);
        cargoEmpleado1.setCode((long) 1);
        EmployeePosition cargoEmpleado2 = new EmployeePosition("Tesoreria", 3000000, 12000000);
        cargoEmpleado2.setCode((long) 2);
        EmployeePosition cargoEmpleado3 = new EmployeePosition("Operario", 1200000, 10000000);
        cargoEmpleado3.setCode((long) 3);
        EmployeePosition cargoEmpleado4 = new EmployeePosition("Ejecutivo", 4000000, 20000000);
        cargoEmpleado4.setCode((long) 4);
        EmployeePosition cargoEmpleado5 = new EmployeePosition("Otros", 1200000, 12000000);
        cargoEmpleado5.setCode((long) 5);
        employeePositionService.save(cargoEmpleado1);
        employeePositionService.save(cargoEmpleado2);
        employeePositionService.save(cargoEmpleado3);
        employeePositionService.save(cargoEmpleado4);
        employeePositionService.save(cargoEmpleado5);
    }

    public void quemarTiposUsuario() {
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

    public void quemarUsuariosEmpleados() {
        UserType administrador=userTypeService.findByLevel("Administrador").get();
        UserType parametrico=userTypeService.findByLevel("Param\u00E9trico").get();
        UserType esporadico=userTypeService.findByLevel("Espor\u00E1dicos").get();
        User usuario1=new User("JUANMA","1234",
                LocalDate.of(2004,5,11),administrador);
        User usuario2=new User("MIGUEL","1234",
                LocalDate.of(2005,5,15),parametrico);
        User usuario3=new User("SANTIAGO","1234",
                LocalDate.of(2004,8,13),esporadico);
        userService.save(usuario1);
        userService.save(usuario2);
        userService.save(usuario3);
        Branch sucursal1=branchService.findByCode((long)1).get();
        Branch sucursal2=branchService.findByCode((long)2).get();
        Branch sucursal3=branchService.findByCode((long)2).get();
        EmployeePosition cargo1=employeePositionService.findByName("Administrador").get();
        EmployeePosition cargo2=employeePositionService.findByName("Tesoreria").get();
        EmployeePosition cargo3=employeePositionService.findByName("Operario").get();
        Employee empleado1=new Employee("Juan Manuel","1",
                false,usuario1,sucursal1,cargo1);
        empleado1.setCode((long)1090272239);
        Employee empleado2=new Employee("Miguel Angel","2",
                false,usuario2,sucursal2,cargo2);
        empleado2.setCode((long)1095550864);
        Employee empleado3=new Employee("Santiago","3",
                false,usuario3,sucursal3,cargo3);
        empleado3.setCode((long)1091884520);
        employeeService.save(empleado1);
        employeeService.save(empleado2);
        employeeService.save(empleado3);
    }

    public void quemarPeriodos() {
        Period periodo1=new Period((float)0.07,24);
        periodo1.setCode((long) 1);
        Period periodo2=new Period((float)0.075,36);
        periodo2.setCode((long) 2);
        Period periodo3=new Period((float)0.08,48);
        periodo3.setCode((long) 3);
        Period periodo4=new Period((float)0.083,60);
        periodo4.setCode((long) 4);
        Period periodo5=new Period((float)0.086,72);
        periodo5.setCode((long) 5);
        periodService.save(periodo1);
        periodService.save(periodo2);
        periodService.save(periodo3);
        periodService.save(periodo4);
        periodService.save(periodo5);
    }

    public void quemarEstadosSolicitud() {
        LoanRequestStatus estadoSolicitud1=new LoanRequestStatus("pendiente");
        estadoSolicitud1.setCode((long) 1);
        LoanRequestStatus estadoSolicitud2=new LoanRequestStatus("en estudio");
        estadoSolicitud2.setCode((long) 2);
        LoanRequestStatus estadoSolicitud3=new LoanRequestStatus("aprobada");
        estadoSolicitud3.setCode((long) 3);
        LoanRequestStatus estadoSolicitud4=new LoanRequestStatus("reprobada");
        estadoSolicitud4.setCode((long) 4);
        loanRequestStatusService.save(estadoSolicitud1);
        loanRequestStatusService.save(estadoSolicitud2);
        loanRequestStatusService.save(estadoSolicitud3);
        loanRequestStatusService.save(estadoSolicitud4);
    }

}
