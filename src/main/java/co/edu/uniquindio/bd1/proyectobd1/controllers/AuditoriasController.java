package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.AuditInfoDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.EmployeeDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.LoanDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AuditoriasController {

    @FXML
    private TableView<AuditInfoDTO> tableAuditorias;

    @FXML
    private TableColumn<AuditInfoDTO, String> columnHoraEntrada;

    @FXML
    private TableColumn<AuditInfoDTO, String> columnFechaSalida;

    @FXML
    private TableColumn<AuditInfoDTO, String> columnUsuario;

    @FXML
    private TableColumn<AuditInfoDTO, String> columnFechaEntrada;

    @FXML
    private TableColumn<AuditInfoDTO, String> columnHoraSalida;

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    /**
     * Metodo que inicializa la clase controlador
     */
    @FXML
    private void initialize() {
        actualizarTablaPresamos();
    }

    private void actualizarTablaPresamos() {
        tableAuditorias.getItems().clear();
        ObservableList<AuditInfoDTO> listaPrestamosProperty= FXCollections.observableList(mfm.obtenerAuditoriasInfo());
        tableAuditorias.setItems(listaPrestamosProperty);
        columnFechaEntrada.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().entryDate()));
        columnHoraEntrada.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().entryTime()));
        columnFechaSalida.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().outputDate()));
        columnHoraSalida.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().outputTime()));
        columnUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().user()));
    }

    @FXML
    void verInformacionEmpleado() {
        AuditInfoDTO auditoria=tableAuditorias.getSelectionModel().getSelectedItem();
        if (auditoria != null) {
            EmployeeDTO empleado=mfm.obtenerEmpleadoUsuario(auditoria.user());
            String msj=organizarInfoEmpleado(empleado);
            InterfazFXUtil.mostrarMensaje("Datos del empleado",msj, Alert.AlertType.INFORMATION);
        } else {
            InterfazFXUtil.mostrarMensaje("Auditoria no seleccionada",
                    "No ha seleccionado ninguna auditoria para ver la informacion del empleado", Alert.AlertType.WARNING);
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
