package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.application.App;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class LoginController {

	private Stage ventana;
	private App aplicacion;
	
	@FXML
	private TextField txtCorreo;

	@FXML
	private TextField txtContrasenia;
	ModelFactoryController mfm=ModelFactoryController.getInstance();

	@FXML
	void iniciarSesion() {
		int opcion;
		try {
			opcion=Integer.parseInt(txtCorreo.getText());
			LocalDate fecha= LocalDate.now();
			mfm.registrarInicioSesion(txtCorreo.getText(),fecha);
			switch(opcion) {
				case 1: iniciarSesionAdministrador("");break;
				case 2: iniciarSesionTesoreria("") ;break;
				case 3: iniciarSesionEmpleado("") ;break;
			}
		} catch (Exception e) {
			InterfazFXUtil.mostrarMensaje("Funcion en construccion",
					"Solo poner en correo:\n\n 1 (Administrador)\n" +
							"2 (Tesoreria)\n3 (Otro empleado)\n\n" +
							"Esto es de prueba", Alert.AlertType.INFORMATION);
		}
	}

	public void setAplicacion(App aplicacion) {
		this.aplicacion = aplicacion;
	}

    public void setVentana(Stage primaryStage) {
		ventana=primaryStage;
    }

	public void iniciarSesionAdministrador(String usuario) {
		ventana.close();
		mfm.setUsuarioSesion(usuario);
		aplicacion.mostrarAdministrador();
	}

	public void iniciarSesionTesoreria(String usuario) {
		ventana.close();
		mfm.setUsuarioSesion(usuario);
		aplicacion.mostrarTesoreria();
	}

	public void iniciarSesionEmpleado(String usuario) {
		ventana.close();
		mfm.setUsuarioSesion(usuario);
		aplicacion.mostrarEmpleado();
	}

	public boolean verificarDatos() {
		boolean sonValidos=true;
		String msj="";
		msj+=InterfazFXUtil.verificarDato(txtCorreo,"","correo")+"\n";
		msj+=InterfazFXUtil.verificarDato(txtContrasenia,"","contrase\u00F1a");
		if (!msj.equals("")) {
			sonValidos=false;
			InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, Alert.AlertType.ERROR);
		}
		return sonValidos;
	}
}
