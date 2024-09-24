package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class PagosTotalesController {

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private TableView<Object> tablePagos;
	
	@FXML
    private TableColumn<Object, String> columnPrestamo;

    @FXML
    private TableColumn<Object, String> columnNumeroCoutas;

    @FXML
    private TableColumn<Object, String> columnFechaPago;

    @FXML
    private TableColumn<Object, String> columnValor;

    @FXML
    private TableColumn<Object, String> columnCorreo;


    /**
     * Metodo que inicializa la clase controlador
     */
    @FXML
    private void initialize() {
        actualizarTabla();
    }

    private void actualizarTabla() {
        tablePagos.getItems().clear();
        ObservableList<Object> listaPagosProperty= FXCollections.observableList(mfm.obtenerPagosEmpleado());
        tablePagos.setItems(listaPagosProperty);
        columnCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnPrestamo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnValor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnNumeroCoutas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnFechaPago.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
    }
    
    @FXML
    void verInformacionEmpleado() {
        Object objetoDTO=tablePagos.getSelectionModel().getSelectedItem();
        if (objetoDTO != null) {
            InterfazFXUtil.mostrarMensaje("Datos del empleado","",AlertType.INFORMATION);
        }
    }

}
