package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.EmployeeRegisterDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.util.List;


public class GestionEmpleadoController {

	ModelFactoryController mfm=ModelFactoryController.getInstance();

	@FXML
	private TextField txtLogin;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtCodigo;

	@FXML
	private ComboBox<String> comboSucursal;

	@FXML
	private ComboBox<String> comboMunicipio;

	@FXML
	private TableView<Object> tableEmpleados;
	
	@FXML
	private TableColumn<Object, String> columnDeuda;

	@FXML
	private TableColumn<Object, String> columnMora;

	@FXML
	private TableColumn<Object, String> columnCargo;

	@FXML
	private TableColumn<Object, String> columnCorreo;

	@FXML
	private TableColumn<Object, String> columnSucursal;

	@FXML
	private TableColumn<Object, String> columnMunicipio;
	
	@FXML
	private TextField txtSucursal;
	
	@FXML
	private TextField txtCorreo;
	
	@FXML
	private ComboBox<String> comboCargo;

	@FXML
	private TextField txtContrasena;

	@FXML
	private TextField txtMunicipio;
	
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		List<String> municipios=mfm.obtenerMunicipios();
		for (String municipio:municipios) {
			comboMunicipio.getItems().add(municipio);
		}
		List<String> cargosEmpleados=mfm.obtenerCargosEmpleados();
		for (String cargo:cargosEmpleados) {
			comboCargo.getItems().add(cargo);
		}
		actualizarTabla();
	}
	
	private void actualizarTabla() {
		tableEmpleados.getItems().clear();
		ObservableList<Object> listaEmpleadosProperty= FXCollections.observableList(mfm.obtenerEmpleados());
		tableEmpleados.setItems(listaEmpleadosProperty);
		columnCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
		columnDeuda.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
		columnMora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
		columnCargo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
		columnSucursal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
	}

	@FXML
	void editar() {
		if(verificarDatos()) {

		}
	}

	@FXML
	void limpiar() {
		txtLogin.setText("");
		txtContrasena.setText("");
		txtNombre.setText("");
		txtCodigo.setText("");
		txtCorreo.setText("");
		comboSucursal.setValue("");
		comboMunicipio.setValue("");
		comboCargo.setValue("");
		txtMunicipio.setText("");
		txtSucursal.setText("");
	}

	@FXML
	void guardar() {
		if(verificarDatos()) {
			EmployeeRegisterDTO empleado=new EmployeeRegisterDTO(
					txtLogin.getText().trim(),
					txtContrasena.getText().trim(),
					txtCodigo.getText().trim(),
					txtNombre.getText().trim(),
					txtCorreo.getText().trim(),
					comboCargo.getValue().trim(),
					comboMunicipio.getValue().trim(),
					comboSucursal.getValue().trim()
			);
			mfm.guardarUsuario(empleado);
		}
	}

	@FXML
	void eliminar() {

	}

	public boolean verificarDatos() {
		boolean sonValidos=true;
		String msj="";
		msj+=InterfazFXUtil.verificarDato(txtCorreo,"","correo")+"\n";
		msj+=InterfazFXUtil.verificarDato(txtContrasena,"","contrasena")+"\n";
		msj+=InterfazFXUtil.verificarDato(comboCargo,"cargo")+"\n";
		msj+=InterfazFXUtil.verificarDato(txtMunicipio,"","municipio")+"\n";
		msj+=InterfazFXUtil.verificarDato(txtSucursal,"","sucursal");
		if (!msj.equals("")) {
			sonValidos=false;
			InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, AlertType.ERROR);
		}
		return sonValidos;
	}

	@FXML
	public void ponerDatos() {
		Object objetoDTO=tableEmpleados.getSelectionModel().getSelectedItem();
		if (objetoDTO != null) {
			txtCorreo.setText(""+objetoDTO);
			txtContrasena.setText(""+objetoDTO);
			comboCargo.setValue(""+objetoDTO);
			txtMunicipio.setText(""+objetoDTO);
			txtSucursal.setText(""+objetoDTO);
		}
	}


	@FXML
	void obtenerSucursales() {
		comboSucursal.getItems().clear();
		List<String> sucursales=mfm.obtenerSucursales(comboMunicipio.getValue());
		for (String sucursal:sucursales) {
			comboSucursal.getItems().add(sucursal);
		}
	}

}
