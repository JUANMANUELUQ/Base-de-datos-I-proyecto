package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.application.App;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AdministradorController implements VentanaTabuladora {

    private App aplicacion;
    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private AnchorPane panel;

    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * Inicializa el controlador.
     */
    @FXML
    void initialize() {
        gestionEmpleado();
    }

    @FXML
    void pagosRealizados() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/Pagos.fxml",panel,getClass());
    }

    @FXML
    void pagoCuotas() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/InformacionPrestamo.fxml",panel,getClass());
    }

    @FXML
    void prestamo() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/Prestamo.fxml",panel,getClass());
    }

    @FXML
    void gestionEmpleado() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/GestionEmpleado.fxml",panel,getClass());
    }

    @FXML
    void reportes() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/ReportesAdministrador.fxml",panel,getClass());
    }

    @FXML
    void municipioSucursal() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/MinicipiosSucursales.fxml",panel,getClass());
    }

    @FXML
    void calculadora() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/Calculadora.fxml",panel,getClass());
    }

    @FXML
    void calendario() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/Calendario.fxml",panel,getClass());
    }

    @FXML
    void ayudas() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/Ayudas.fxml",panel,getClass());
    }



    /**
     * Cierra la sesión del usuario actual y muestra la pantalla de inicio de sesión.
     */
    @FXML
    void cerrarSesion() {
        Platform.runLater(this::cerrarSesionDespues);
    }

    void cerrarSesionDespues() {
        aplicacion.mostrarLogin();
        mfm.cerrarSesion();
    }


}
