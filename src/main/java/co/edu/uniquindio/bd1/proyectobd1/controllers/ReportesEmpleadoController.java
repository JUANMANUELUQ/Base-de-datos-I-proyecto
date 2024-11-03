package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.*;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import co.edu.uniquindio.bd1.proyectobd1.utils.PDF_Util;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        LoanDTO prestamo=tablePrestamo.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            File ruta= InterfazFXUtil.seleccionarCarpeta();
            if (ruta!=null) {
                List<ReportCalculatedPaymentsDTO> datos=mfm.obtenerDatosPagosCalculados(prestamo.numberLoan());
                String fecha=obtenerFechaFormateada(LocalDateTime.now());
                String reporte=generarCodigoReporteCuotasCalculadas(datos);
                PDF_Util.generarPDF(reporte,ruta.getAbsolutePath(),"CuotasCalculadas"+fecha);
            }
        } else {
            InterfazFXUtil.mostrarMensaje("Prestamo no seleccionado",
                    "No ha seleccionado ningun prestamo para calcular sus coutas", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void solicitudesRealizadas() {
        File ruta=InterfazFXUtil.seleccionarCarpeta();
        if (ruta!=null) {
            List<ReportLoanRequestDTO> datos=mfm.obtenerDatosSolicitudesRealizadas();
            String fecha=obtenerFechaFormateada(LocalDateTime.now());
            String reporte=generarCodigoReporteSolicitudesRealizadas(datos);
            PDF_Util.generarPDF(reporte,ruta.getAbsolutePath(),"SolucitudesRealizadas"+fecha);
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

    String generarCodigoReporteSolicitudesRealizadas(List<ReportLoanRequestDTO> datos) {
        String html= "<html>" +
                "<head><title>Solicitudes realizadas</title></head>" +
                "<body>" +
                "<h1 style='text-align:center'>Solicitudes realizadas</h1>" +
                "<table border='1' style='width:100%;border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='background-color:#d3d3d3'>Fecha de solicitud</th>" +
                "<th style='background-color:#d3d3d3'>Estado</th>" +
                "<th style='background-color:#d3d3d3'>Valor solicitado</th>" +
                "</tr>";
        for (ReportLoanRequestDTO dto : datos) {
            html+="<tr><td>"+dto.date()+"</td>"+
                    "<td>"+dto.status()+"</td>"+
                    "<td>"+dto.amount()+"</td></tr>";
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
