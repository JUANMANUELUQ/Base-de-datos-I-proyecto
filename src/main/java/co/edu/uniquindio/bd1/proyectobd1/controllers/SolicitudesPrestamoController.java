package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.*;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class SolicitudesPrestamoController {
	
    @FXML
    private TableView<LoanRequestInfoDTO> tablePrestamos;

    @FXML
    private TableColumn<LoanRequestInfoDTO, String> columnPeriodoMeses;

    @FXML
    private TableColumn<LoanRequestInfoDTO, String> columnCorreo;

    @FXML
    private TableColumn<LoanRequestInfoDTO, String> columnMonto;

    @FXML
    private TableColumn<LoanRequestInfoDTO, String> columnFechaSolicitud;

    @FXML
    private TableColumn<LoanRequestInfoDTO, String> columnEstado;

    ModelFactoryController mfm=ModelFactoryController.getInstance();
    
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		actualizarTabla();
	}
	
	private void actualizarTabla() {
        tablePrestamos.getItems().clear();
        ObservableList<LoanRequestInfoDTO> listaSolicitudesProperty= FXCollections.observableList(mfm.obtenerSolicitudesPrestamo());
        tablePrestamos.setItems(listaSolicitudesProperty);
        columnCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().emailEmployee()));
        columnFechaSolicitud.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().requestDate()));
        columnMonto.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.0f",cellData.getValue().amount())));
        columnPeriodoMeses.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().periodMonths()));
        columnEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status()));
	}

    @FXML
    void aprobar() {
        LoanRequestInfoDTO loanRequestInfoDTO=tablePrestamos.getSelectionModel().getSelectedItem();
        if (loanRequestInfoDTO != null) {
            mfm.cambiarEstadoSolicitud(new ChangeLoanRequestStateDTO(
                    loanRequestInfoDTO.loanNumber(),
                    "aprobada"
            ));
            actualizarTabla();
        } else {
            InterfazFXUtil.mostrarMensaje("Solicitud no seleccionada",
                    "No ha seleccionado ningun prestamo", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void rechazar() {
        LoanRequestInfoDTO loanRequestInfoDTO=tablePrestamos.getSelectionModel().getSelectedItem();
        if (loanRequestInfoDTO != null) {
            mfm.cambiarEstadoSolicitud(new ChangeLoanRequestStateDTO(
                    loanRequestInfoDTO.loanNumber(),
                    "reprobada"
            ));
            actualizarTabla();
        } else {
            InterfazFXUtil.mostrarMensaje("Solicitud no seleccionada",
                    "No ha seleccionado ningun prestamo", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void ponerEstudio() {
        LoanRequestInfoDTO loanRequestInfoDTO=tablePrestamos.getSelectionModel().getSelectedItem();
        if (loanRequestInfoDTO != null) {
            mfm.cambiarEstadoSolicitud(new ChangeLoanRequestStateDTO(
                    loanRequestInfoDTO.loanNumber(),
                    "en estudio"
            ));
            actualizarTabla();
        } else {
            InterfazFXUtil.mostrarMensaje("Solicitud no seleccionada",
                    "No ha seleccionado ningun prestamo", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void verInformacionSolicitante() {
        LoanRequestInfoDTO loanRequestInfoDTO=tablePrestamos.getSelectionModel().getSelectedItem();
        if (loanRequestInfoDTO != null) {
            EmployeeDTO empleado=mfm.obtenerEmpleadoCorreo(loanRequestInfoDTO.emailEmployee());
            String msj=organizarInfoEmpleado(empleado);
            InterfazFXUtil.mostrarMensaje("Datos del empleado",msj,Alert.AlertType.INFORMATION);
        } else {
            InterfazFXUtil.mostrarMensaje("Solicitud no seleccionada",
                    "No ha seleccionado ningun prestamo", Alert.AlertType.WARNING);
        }
    }

    public String organizarInfoEmpleado(EmployeeDTO empleado) {
        String msj="";
        msj+="Nombre de usuario: "+empleado.login()+"\n";
        msj+="Fecha de creacion: "+empleado.creationDate()+"\n";
        msj+="Nombre: "+empleado.name()+"\n";
        msj+="Correo: "+empleado.email()+"\n";
        msj+="Esta en mora: ";
        if (empleado.arrears()) {
            msj+="Si\n";
        } else  {
            msj+="No\n";
        }
        msj+="Cargo: "+empleado.position()+"\n";
        msj+="Salario: "+empleado.salary()+"\n";
        msj+="Sucursal: "+empleado.branch()+"\n";
        msj+="Municipio: "+empleado.municipality()+"\n";
        return msj;
    }

}
