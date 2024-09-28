package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

public class PagosTotalesController {

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private TableView<Object> tablePrestamo;

    @FXML
    private TableColumn<Object, String> columnNumeroPrestamoP;

    @FXML
    private TableColumn<Object, String> columnFechaSolicitud;

    @FXML
    private TableColumn<Object, String> columnMonto;

    @FXML
    private TableColumn<Object, String> columnPeriodoMeses;

    @FXML
    private TableColumn<Object, String> columnTasaInteres;

    @FXML
    private TableView<Object> tablePagos;

    @FXML
    private TableColumn<Object, String> columnNumeroCoutas;

    @FXML
    private TableColumn<Object, String> columnFechaPago;

    @FXML
    private TableColumn<Object, String> columnNumeroPrestamoC;

    @FXML
    private TableColumn<Object, String> columnValor;


    @FXML
    void ponerDatosCuotas() {
        Object objetoDTO=tablePrestamo.getSelectionModel().getSelectedItem();
        if (objetoDTO != null) {
            actualizarTablaCuotasPago(new ArrayList<Object>());
        }
    }


    /**
     * Metodo que inicializa la clase controlador
     */
    @FXML
    private void initialize() {
        actualizarTablaPresamos();
    }

    @FXML
    private void verInformacionEmpleado() {
        Object objetoDTO=tablePrestamo.getSelectionModel().getSelectedItem();
        if (objetoDTO != null) {

        } else {
            InterfazFXUtil.mostrarMensaje("Prestamo no seleccionado",
                    "No ha seleccionado ningun prestamo para ver la informacion del empleado", AlertType.WARNING);
        }
    }

    private void actualizarTablaPresamos() {
        tablePrestamo.getItems().clear();
        ObservableList<Object> listaPrestamosProperty= FXCollections.observableList(mfm.obtenerPagosTotales());
        tablePrestamo.setItems(listaPrestamosProperty);
        columnNumeroPrestamoP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnFechaSolicitud.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnMonto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnPeriodoMeses.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnValor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnTasaInteres.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
    }

    private void actualizarTablaCuotasPago(ArrayList<Object> listaDTOPrestamos) {
        tablePagos.getItems().clear();
        ObservableList<Object> listaPagosProperty= FXCollections.observableList(listaDTOPrestamos);
        tablePagos.setItems(listaPagosProperty);
        columnNumeroPrestamoC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnValor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnNumeroCoutas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
        columnFechaPago.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()+""));
    }

}
