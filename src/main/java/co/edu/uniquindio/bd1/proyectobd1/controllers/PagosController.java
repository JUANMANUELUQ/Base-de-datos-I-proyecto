package co.edu.uniquindio.bd1.proyectobd1.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PagosController {

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
        columnPrestamo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnValor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnNumeroCoutas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnFechaPago.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
    }

}
