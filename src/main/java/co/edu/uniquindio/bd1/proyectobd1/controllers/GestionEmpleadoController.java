package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.EmployeeDeleteDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.EmployeeRegisterDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.EmployeeUpdateDTO;
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
	private TableView<EmployeeRegisterDTO> tableEmpleados;
	
	@FXML
	private TableColumn<EmployeeRegisterDTO, String> columnDeuda;

	@FXML
	private TableColumn<EmployeeRegisterDTO, String> columnCargo;

	@FXML
	private TableColumn<EmployeeRegisterDTO, String> columnCorreo;

	@FXML
	private TableColumn<EmployeeRegisterDTO, String> columnSucursal;

	@FXML
	private TableColumn<EmployeeRegisterDTO, String> columnMunicipio;
	
	@FXML
	private TextField txtCorreo;
	
	@FXML
	private ComboBox<String> comboCargo;

	@FXML
	private TextField txtContrasena;
	
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
		ObservableList<EmployeeRegisterDTO> listaEmpleadosProperty= FXCollections.observableList(mfm.obtenerEmpleados());
		tableEmpleados.setItems(listaEmpleadosProperty);
		columnCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));
		columnCargo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().position()));
		columnMunicipio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().municipality()));
		columnSucursal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().branch()));
	}

	@FXML
	void editar() {
		EmployeeRegisterDTO employeeRegisterDTO=tableEmpleados.getSelectionModel().getSelectedItem();
		if (employeeRegisterDTO != null) {
			if (verificarDatos()) {
				EmployeeUpdateDTO empleado = new EmployeeUpdateDTO(
						txtLogin.getText().trim(),
						txtContrasena.getText().trim(),
						Long.parseLong(txtCodigo.getText()),
						txtNombre.getText().trim(),
						txtCorreo.getText().trim(),
						comboCargo.getValue().trim(),
						comboMunicipio.getValue().trim(),
						comboSucursal.getValue().trim(),
						employeeRegisterDTO.login(),
						employeeRegisterDTO.code(),
						employeeRegisterDTO.email()
						);
                try {
                    mfm.actualizarUsuario(empleado);
					actualizarTabla();
                } catch (Exception e) {
					InterfazFXUtil.mostrarMensaje("Error al actualizar el usuario",e.getMessage(),AlertType.WARNING);
					e.printStackTrace();
                }
            }
		}
	}

	@FXML
	void limpiar() {

		txtLogin.setText("");
		txtContrasena.setText("");
		txtCodigo.setText("");
		txtNombre.setText("");
		txtCorreo.setText("");
		comboCargo.setValue("");
		comboMunicipio.setValue("");
		comboSucursal.setValue("");
	}

	@FXML
	void guardar() {
		if(verificarDatos()) {
			EmployeeRegisterDTO empleado=new EmployeeRegisterDTO(
					txtLogin.getText().trim(),
					txtContrasena.getText().trim(),
					Long.parseLong(txtCodigo.getText()),
					txtNombre.getText().trim(),
					txtCorreo.getText().trim(),
					comboCargo.getValue().trim(),
					comboMunicipio.getValue().trim(),
					comboSucursal.getValue().trim()
			);
            try {
                mfm.guardarUsuario(empleado);
				actualizarTabla();
            } catch (Exception e) {
                InterfazFXUtil.mostrarMensaje("Error al guardar el usuario",e.getMessage(),AlertType.WARNING);
            }
        }
	}

	@FXML
	void eliminar() {
		EmployeeRegisterDTO employeeRegisterDTO=tableEmpleados.getSelectionModel().getSelectedItem();
		if (employeeRegisterDTO != null) {
			mfm.borrarUsuario(new EmployeeDeleteDTO(
					employeeRegisterDTO.login(),
					employeeRegisterDTO.code()
			));
		}
	}

	public boolean verificarDatos() {
		boolean sonValidos=true;
		String msj="";
		msj+=InterfazFXUtil.verificarDato(txtLogin,"","login");
		msj+=InterfazFXUtil.verificarDato(txtContrasena,"","contrasena");
		msj+=InterfazFXUtil.verificarDato(txtCodigo,"","codigo");
		msj+=InterfazFXUtil.verificarDato(txtNombre,"","nombre");
		msj+=InterfazFXUtil.verificarDato(txtCorreo,"","correo");
		msj+=InterfazFXUtil.verificarDato(comboCargo,"cargo");
		msj+=InterfazFXUtil.verificarDato(comboMunicipio,"municipio");
		msj+=InterfazFXUtil.verificarDato(comboSucursal,"sucursal");
		if (!msj.equals("")) {
			sonValidos=false;
			InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, AlertType.ERROR);
		}
		return sonValidos;
	}

	@FXML
	public void ponerDatos() {
		EmployeeRegisterDTO employeeRegisterDTO=tableEmpleados.getSelectionModel().getSelectedItem();
		if (employeeRegisterDTO != null) {
			txtLogin.setText(employeeRegisterDTO.login());
			txtContrasena.setText(employeeRegisterDTO.password());
			txtCodigo.setText(employeeRegisterDTO.code().toString());
			txtNombre.setText(employeeRegisterDTO.name());
			txtCorreo.setText(employeeRegisterDTO.email());
			comboCargo.setValue(employeeRegisterDTO.position());
			comboMunicipio.setValue(employeeRegisterDTO.municipality());
			comboSucursal.setValue(employeeRegisterDTO.branch());
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
