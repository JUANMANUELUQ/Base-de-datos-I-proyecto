package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.application.App;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class EmpleadoController implements VentanaTabuladora {

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
        prestamo();
    }

    @FXML
    void prestamo() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/Prestamo.fxml",panel,getClass());
    }

    @FXML
    void pagoCuotas() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/InformacionPrestamo.fxml",panel,getClass());
    }

    @FXML
    void pagosRealizados() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/Pagos.fxml",panel,getClass());
    }

    /**
     * Cierra la sesión del usuario actual y muestra la pantalla de inicio de sesión.
     */
    @FXML
    void cerrarSesion() {
        aplicacion.mostrarLogin();
        mfm.setUsuarioSesion(null);
    }

}
