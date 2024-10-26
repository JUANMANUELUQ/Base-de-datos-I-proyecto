package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.CreateLoanPayment;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InformacionPrestamoController {
	
	@FXML
	private TextField txtNumPrestamo;

	@FXML
	private TextField txtNumCouta;

	@FXML
	private TextField txtValor;

	@FXML
	private DatePicker fechaPago;

	ModelFactoryController mfm=ModelFactoryController.getInstance();

	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		fechaPago.setEditable(false);
		fechaPago.setStyle("-fx-background-color: #aaaaaa;");
		fechaPago.getEditor().setStyle("-fx-background-color: #cccccc;");
	}

	public boolean verificarDatos() {
		boolean sonValidos=true;
		String msj="";
		msj+= InterfazFXUtil.verificarDatoNumericoEntero(txtNumPrestamo,"",true);
		if (!mfm.existePrestamoUsuarioSesion(Long.parseLong(txtNumPrestamo.getText()))) {
			msj+="No existe un prestamo con el numero indicado relacionado a usted";
		}
		msj+= InterfazFXUtil.verificarDatoNumericoEntero(txtNumCouta,"",true);
		if (fechaPago.getValue()==null) {
			msj+="Debe indicar la fecha de pago";
		}
		msj+= InterfazFXUtil.verificarDatoNumericoReal(txtValor,"",true);
		if (!msj.equals("")) {
			sonValidos=false;
			InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, Alert.AlertType.ERROR);
		}
		return sonValidos;
	}

	@FXML
	void enviarDatos() {
		if(verificarDatos()) {
			mfm.registrarPago(new CreateLoanPayment(
					Long.parseLong(txtNumPrestamo.getText()),
					Integer.parseInt(txtNumCouta.getText()),
					fechaPago.getValue(),
					Float.parseFloat(txtValor.getText())
			));
			InterfazFXUtil.mostrarMensaje("Entradas no validas",
					"Se registro correctamente el pago", Alert.AlertType.INFORMATION);
		}
	}

	//

}
