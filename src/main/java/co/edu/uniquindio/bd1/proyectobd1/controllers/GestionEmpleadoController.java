package co.edu.uniquindio.bd1.proyectobd1.controllers;

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


public class GestionEmpleadoController {

	ModelFactoryController mfm=ModelFactoryController.getInstance();

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
		txtCorreo.setText("");
		txtContrasena.setText("");
		comboCargo.setValue("");
		txtMunicipio.setText("");
		txtSucursal.setText("");
	}

	@FXML
	void guardar() {
		if(verificarDatos()) {

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

}
