package co.edu.uniquindio.bd1.proyectobd1.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PagosController {

    @FXML
    private TableView<?> tablePagos;

    @FXML
    private TableColumn<?, String> columnPrestamo;

    @FXML
    private TableColumn<?, String> columnNumeroCoutas;

    @FXML
    private TableColumn<?, String> columnFechaPago;

    @FXML
    private TableColumn<?, String> columnValor;

}
