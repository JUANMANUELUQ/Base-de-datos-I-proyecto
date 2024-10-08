package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.application.App;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class TesoreriaController implements VentanaTabuladora {

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
        solicitudes();
    }

    @FXML
    void solicitudes() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/SolicitudesPrestamo.fxml",panel,getClass());
    }

    @FXML
    void pagosTotales() {
        InterfazFXUtil.cargarVentana("/co/edu/uniquindio/bd1/proyectobd1/fxml/PagosTotales.fxml",panel,getClass());
    }

    /**
     * Cierra la sesión del usuario actual y muestra la pantalla de inicio de sesión.
     */
    @FXML
    void cerrarSesion() {
        aplicacion.mostrarLogin();
        mfm.setEmpleadoSesion(null);
    }

}
