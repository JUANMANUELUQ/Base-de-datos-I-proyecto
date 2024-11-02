package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.LoanDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReportesEmpleadoController {

    @FXML
    private TableView<LoanDTO> tablePrestamo;

    @FXML
    private TableColumn<LoanDTO, String> columnTasaInteres;

    @FXML
    private TableColumn<LoanDTO, String> columnNumeroPrestamoP;

    @FXML
    private TableColumn<LoanDTO, String> columnPeriodoMeses;

    @FXML
    private TableColumn<LoanDTO, String> columnMonto;

    @FXML
    private TableColumn<LoanDTO, String> columnFechaSolicitud;

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private void initialize() {
        actualizarTablaPresamos();
    }

    private void actualizarTablaPresamos() {
        tablePrestamo.getItems().clear();
        ObservableList<LoanDTO> listaPrestamosProperty= FXCollections.observableList(mfm.obtenerPagosEmpleadoSesion());
        tablePrestamo.setItems(listaPrestamosProperty);
        columnNumeroPrestamoP.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().numberLoan()));
        columnFechaSolicitud.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().creationDate()));
        columnMonto.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.0f",cellData.getValue().amount())));
        columnPeriodoMeses.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().periodMonths()));
        columnTasaInteres.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().interestRate()));
    }

    @FXML
    void cuotasCalculadas() {

    }

    @FXML
    void solicitudesRealizadas() {

    }

}
