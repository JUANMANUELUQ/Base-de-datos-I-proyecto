package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.ReportBranchTotalDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.ReportMunicipalityTotalDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import co.edu.uniquindio.bd1.proyectobd1.utils.PDF_Util;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportesAdministradorController {

    @FXML
    private TableColumn<String, String> columnMunicipioNombre;

    @FXML
    private Label lblMunicipio;

    @FXML
    private TableView<String> tableSucursales;

    @FXML
    private TableColumn<String, String> columnSucursalNombre;

    @FXML
    private TableView<String> tableMunicipio;

    @FXML
    private Label lblSucursal;

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private void initialize() {
        actualizarTablaMunicios();
        lblMunicipio.setText("");
        lblSucursal.setText("");
    }

    @FXML
    void seleccionarSucursal() {
        String sucursal=tableSucursales.getSelectionModel().getSelectedItem();
        if (sucursal != null) {
            lblSucursal.setText(sucursal);
        }
    }

    @FXML
    void seleccionarMunicipio() {
        String prestamo=tableMunicipio.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            lblMunicipio.setText(prestamo);
            actualizarTablaSucursales(prestamo);
        }
    }

    private void actualizarTablaMunicios() {
        ObservableList<String> listaPrestamosProperty= FXCollections.observableList(mfm.obtenerMunicipios());
        tableMunicipio.setItems(listaPrestamosProperty);
        columnMunicipioNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    }

    @FXML
    void totalMunicipio() {
        File ruta=InterfazFXUtil.seleccionarCarpeta();
        if (ruta!=null) {
            List<ReportMunicipalityTotalDTO> datos=mfm.obtenerDatosTotalPrestamoMunicipios();
            String fecha=obtenerFechaFormateada(LocalDateTime.now());
            String reporte= generarCodigoReporteMunicipios(datos);
            PDF_Util.generarPDF(reporte,ruta.getAbsolutePath(),"TotalMunicipios"+fecha);
        }
    }

    @FXML
    void totalSucursal() {
        if (!lblMunicipio.getText().equals("")) {
            File ruta=InterfazFXUtil.seleccionarCarpeta();
            if (ruta!=null) {
                List<ReportBranchTotalDTO> datos=mfm.obtenerDatosTotalPrestamoSucursales(lblMunicipio.getText());
                String fecha=obtenerFechaFormateada(LocalDateTime.now());
                String reporte=generarCodigoReporteSucursales(datos);
                PDF_Util.generarPDF(reporte,ruta.getAbsolutePath(),"TotalSucursales"+fecha);
            }
        } else {
            InterfazFXUtil.mostrarMensaje("Municipio no seleccionado",
                    "No ha seleccionado ningun municipio", Alert.AlertType.WARNING);
        }
    }

    void actualizarTablaSucursales(String municipio) {
        tableSucursales.getItems().clear();
        ObservableList<String> listaSucursalesProperty=
                FXCollections.observableList(mfm.obtenerSucursales(municipio));
        tableSucursales.setItems(listaSucursalesProperty);
        columnSucursalNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    }

    String generarCodigoReporteMunicipios(List<ReportMunicipalityTotalDTO> datos) {
        String html= "<html>" +
                "<head><title>Total prestado por municipio</title></head>" +
                "<body>" +
                "<h1 style='text-align:center'>Total prestado por municipio</h1>" +
                "<table border='1' style='width:100%;border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='background-color:#d3d3d3'>Municipio</th>" +
                "<th style='background-color:#d3d3d3'>Total</th>" +
                "<th style='background-color:#d3d3d3'>Cantidad de prestamos</th>" +
                "<th style='background-color:#d3d3d3'>Cantidad de empleados</th>" +
                "</tr>";
        for (ReportMunicipalityTotalDTO dto : datos) {
            html+="<tr><td>"+dto.municipality()+"</td>"+
                    "<td>"+dto.total()+"</td>"+
                    "<td>"+dto.loansCuantity()+"</td>"+
                    "<td>"+dto.employeeCuantity()+"</td></tr>";
        }
        html+="</table>" +
                "</body></html>";
        return html;
    }

    String generarCodigoReporteSucursales(List<ReportBranchTotalDTO> datos) {
        String html= "<html>" +
                "<head><title>Total prestado por sucursal</title></head>" +
                "<body>" +
                "<h1 style='text-align:center'>Total prestado por sucursal</h1>" +
                "<table border='1' style='width:100%;border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='background-color:#d3d3d3'>Sucursal</th>" +
                "<th style='background-color:#d3d3d3'>Total</th>" +
                "<th style='background-color:#d3d3d3'>Cantidad de prestamos</th>" +
                "<th style='background-color:#d3d3d3'>Cantidad de empleados</th>" +
                "</tr>";
        for (ReportBranchTotalDTO dto : datos) {
            html+="<tr><td>"+dto.branch()+"</td>"+
                    "<td>"+dto.total()+"</td>"+
                    "<td>"+dto.loansCuantity()+"</td>"+
                    "<td>"+dto.employeeCuantity()+"</td></tr>";
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
