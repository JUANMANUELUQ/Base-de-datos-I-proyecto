package co.edu.uniquindio.bd1.proyectobd1.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SolcitudesPrestamoController {
	
    @FXML
    private TableView<?> tablePrestamos;

    @FXML
    private TableColumn<?, String> columnPeriodoMeses;

    @FXML
    private TableColumn<?, String> columnCorreo;

    @FXML
    private TableColumn<?, String> columnMonto;

    @FXML
    private TableColumn<?, String> columnFechaSolicitud;
    
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		actualizarTabla();
	}
	
	private void actualizarTabla() {
		
	}

    @FXML
    void aprobar() {

    }

    @FXML
    void rechazar() {

    }

    @FXML
    void verInformacionSolicitante() {

    }

}
