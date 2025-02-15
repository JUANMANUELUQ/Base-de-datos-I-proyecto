package co.edu.uniquindio.bd1.proyectobd1;

import co.edu.uniquindio.bd1.proyectobd1.application.App;
import co.edu.uniquindio.bd1.proyectobd1.controllers.ModelFactoryController;
import co.edu.uniquindio.bd1.proyectobd1.service.implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication(proxyBeanMethods = false)
public class Main implements CommandLineRunner {

    @Autowired
    private AuditServiceImp auditService;
    @Autowired
    private BranchServiceImp branchService;
    @Autowired
    private EmployeePositionServiceImp employeePositionService;
    @Autowired
    private EmployeeServiceImp employeeService;
    @Autowired
    private MunicipalityServiceImp municipalityService;
    @Autowired
    private UserServiceImp userService;
    @Autowired
    private UserTypeServiceImp userTypeService;
    @Autowired
    private LoanServiceImp loanService;
    @Autowired
    private LoanRequestServiceImp loanRequestService;
    @Autowired
    private LoanRequestStatusServiceImp loanRequestStatusService;
    @Autowired
    private PeriodServiceImp periodService;
    @Autowired
    private LoanPaymentServiceImp loanPaymentService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ModelFactoryController mfm=ModelFactoryController.getInstance();
        mfm.setAuditService(auditService);
        mfm.setBranchService(branchService);
        mfm.setEmployeePositionService(employeePositionService);
        mfm.setEmployeeService(employeeService);
        mfm.setMunicipalityService(municipalityService);
        mfm.setUserService(userService);
        mfm.setUserTypeService(userTypeService);
        mfm.setLoanService(loanService);
        mfm.setLoanRequestService(loanRequestService);
        mfm.setLoanRequestStatusService(loanRequestStatusService);
        mfm.setPeriodService(periodService);
        mfm.setLoanPaymentService(loanPaymentService);
        Application.launch(App.class, args);
    }

}
