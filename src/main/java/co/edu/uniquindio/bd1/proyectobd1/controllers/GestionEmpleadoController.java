package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class GestionEmpleadoController {
	
	@FXML
	private TableView<?> tableEmpleados;
	
	@FXML
	private TableColumn<?, String> columnDeuda;

	@FXML
	private TableColumn<?, String> columnMora;

	@FXML
	private TableColumn<?, String> columnCargo;

	@FXML
	private TableColumn<?, String> columnCorreo;

	@FXML
	private TableColumn<?, String> columnSucursal;

	@FXML
	private TableColumn<?, String> columnMunicipio;
	
	@FXML
	private TextField txtSucursal;
	
	@FXML
	private TextField txtCorreo;
	
	@FXML
	private ComboBox comboCargo;

	@FXML
	private TextField txtContrasena;

	@FXML
	private TextField txtMunicipio;
	
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		actualizarTabla();
	}
	
	private void actualizarTabla() {
		
	}

	@FXML
	void editar() {

	}

	@FXML
	void limpiar() {

	}

	@FXML
	void guardar() {

	}

	@FXML
	void eliminar() {

	}

	public boolean verificarDatos() {
		boolean sonValidos=true;
		String msj="";
		/*
		msj+=InterfazFXUtil;
		msj+=InterfazFXUtil;
		msj+=InterfazFXUtil;
		msj+=InterfazFXUtil;
		msj+=InterfazFXUtil;
		*/
		if (!msj.equals("")) {
			sonValidos=false;
			InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, AlertType.ERROR);
		}
		return sonValidos;
	}

}
