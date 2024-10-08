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
		String nivelUsuario = mfm.iniciarSesion(txtCorreo.getText().trim(), txtContrasenia.getText().trim());
		if (nivelUsuario.equals("")) {
			InterfazFXUtil.mostrarMensaje("Usuario no encontrado","No se encontro n usuario con los datos ingresados");
		} else {
			mfm.registrarInicioSesion(txtCorreo.getText(), LocalDate.now());
			switch (nivelUsuario) {
				case "Administrador":
					iniciarSesionAdministrador(txtCorreo.getText());
					break;
				case "Param\u00E9trico":
					iniciarSesionTesoreria(txtCorreo.getText());
					break;
				case "Espor\u00E1dicos":
					iniciarSesionEmpleado(txtCorreo.getText());
					break;
			}
		}

	}


	public void iniciarSesionAdministrador(String usuario) {
		ventana.close();
		aplicacion.mostrarAdministrador();
	}

	public void iniciarSesionTesoreria(String usuario) {
		ventana.close();
		aplicacion.mostrarTesoreria();
	}

	public void iniciarSesionEmpleado(String usuario) {
		ventana.close();
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

	public void setAplicacion(App aplicacion) {
		this.aplicacion = aplicacion;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

}
