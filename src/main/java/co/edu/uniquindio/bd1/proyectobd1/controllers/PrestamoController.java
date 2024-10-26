package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.CreateLoanRequestDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class PrestamoController {
	
	@FXML
    private TextField txtMonto;

    @FXML
    private ComboBox<String> comboPeriodoMeses;

    @FXML
    private Label lblTope;

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private void initialize() {
        List<Integer> periodoMeses = mfm.obtenerPeriodosNombres();
        for (Integer periodo:periodoMeses) {
            comboPeriodoMeses.getItems().add(""+periodo);
        }
        lblTope.setText(String.format("%.0f",mfm.obtenerTopeEmpleadoSesion()));
    }

    public boolean verificarDatos() {
        boolean sonValidos=true;
        String msj="";
        msj+=InterfazFXUtil.verificarDatoNumericoReal(txtMonto,"monto",true);
        if (msj.equals("") && Float.parseFloat(lblTope.getText())<Float.parseFloat(txtMonto.getText())) {
            msj+="El monto indicado supero el topee permitido\n";
        }
        msj+=InterfazFXUtil.verificarDato(comboPeriodoMeses,"periodo meses");
        if (!msj.equals("")) {
            sonValidos=false;
            InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, Alert.AlertType.ERROR);
        }
        return sonValidos;
    }

    @FXML
    void solicitarPrestamo() {
        if (verificarDatos()) {
            mfm.crearSolicitudPrestamo(new CreateLoanRequestDTO(
                    Float.parseFloat(txtMonto.getText()),
                    Integer.parseInt(comboPeriodoMeses.getValue())
            ));
            InterfazFXUtil.mostrarMensaje("Solicitud realizada",
                    "Se realizo la solicitud de prestamo exitosamente", Alert.AlertType.INFORMATION);
        }
    }

}
