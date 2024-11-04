package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.CreateLoanRequestDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.ReportCalculatedPaymentsDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import co.edu.uniquindio.bd1.proyectobd1.utils.PDF_Util;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            File ruta= InterfazFXUtil.seleccionarCarpeta();
            if (ruta!=null) {
                CreateLoanRequestDTO prestamo=new CreateLoanRequestDTO(
                        Float.parseFloat(txtMonto.getText()),
                        Integer.parseInt(comboPeriodoMeses.getValue())
                );
                mfm.crearSolicitudPrestamo(prestamo);
                List<ReportCalculatedPaymentsDTO> datos=mfm.obtenerDatosPagosCalculados(prestamo);
                String fecha=obtenerFechaFormateada(LocalDateTime.now());
                String reporte=generarCodigoReporteCuotasCalculadas(datos);
                PDF_Util.generarPDF(reporte,ruta.getAbsolutePath(),"CuotasCalculadas"+fecha);
                InterfazFXUtil.mostrarMensaje("Solicitud realizada",
                        "Se realizo la solicitud de prestamo exitosamente", Alert.AlertType.INFORMATION);
            } else {
                InterfazFXUtil.mostrarMensaje("Ruta no escogida",
                        "Debe selecionar la ruta donde se generara un reporte informandole como " +
                                "debe pagar las cuotas", Alert.AlertType.INFORMATION);
            }
        }
    }

    String generarCodigoReporteCuotasCalculadas(List<ReportCalculatedPaymentsDTO> datos) {
        String html= "<html>" +
                "<head><title>Cuotas de prestamo</title></head>" +
                "<body>" +
                "<h1 style='text-align:center'>Cuotas de prestamo</h1>" +
                "<table border='1' style='width:100%;border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='background-color:#d3d3d3'>Numero de pago</th>" +
                "<th style='background-color:#d3d3d3'>Valor</th>" +
                "</tr>";
        for (ReportCalculatedPaymentsDTO dto : datos) {
            html+="<tr><td>"+dto.numberPayment()+"</td>"+
                    "<td>"+dto.value()+"</td></tr>";
        }
        html+="</table>" +
                "</body></html>";
        return html;
    }

    String obtenerFechaFormateada(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
        return formatter.format(fecha);
    }

}
