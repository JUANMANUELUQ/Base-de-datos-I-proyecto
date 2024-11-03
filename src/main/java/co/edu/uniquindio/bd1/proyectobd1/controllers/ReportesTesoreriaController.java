package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.EmployeeTotalDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.ReportEmployeeArrearstDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.ReportMunicipalityTotalDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import co.edu.uniquindio.bd1.proyectobd1.utils.PDF_Util;
import javafx.fxml.FXML;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportesTesoreriaController {

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    void morosos() {
        File ruta= InterfazFXUtil.seleccionarCarpeta();
        if (ruta!=null) {
            List<ReportEmployeeArrearstDTO> datos=mfm.obtenerDatosEmpleadosMororsos();
            String fecha=obtenerFechaFormateada(LocalDateTime.now());
            String reporte= generarCodigoReporteMorosos(datos);
            PDF_Util.generarPDF(reporte,ruta.getAbsolutePath(),"EmpleadosMorosos"+fecha);
        }
    }

    @FXML
    void totalSolicitado() {
        File ruta=InterfazFXUtil.seleccionarCarpeta();
        if (ruta!=null) {
            List<EmployeeTotalDTO> datos=mfm.obtenerDatosTotalEmpleados();
            String fecha=obtenerFechaFormateada(LocalDateTime.now());
            String reporte= generarCodigoReporteTotalSolicitado(datos);
            PDF_Util.generarPDF(reporte,ruta.getAbsolutePath(),"TotalEmpleado"+fecha);
        }
    }

    String generarCodigoReporteMorosos(List<ReportEmployeeArrearstDTO> datos) {
        String html= "<html>" +
                "<head><title>Empleadoss morosos</title></head>" +
                "<body>" +
                "<h1 style='text-align:center'>Empleadoss morosos</h1>" +
                "<table border='1' style='width:100%;border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='background-color:#d3d3d3'>Cedula</th>" +
                "<th style='background-color:#d3d3d3'>Nombre</th>" +
                "<th style='background-color:#d3d3d3'>Correo</th>" +
                "<th style='background-color:#d3d3d3'>Deuda</th>" +
                "</tr>";
        for (ReportEmployeeArrearstDTO dto : datos) {
            html+="<tr><td>"+dto.code()+"</td>"+
                    "<td>"+dto.name()+"</td>"+
                    "<td>"+dto.email()+"</td>"+
                    "<td>"+dto.debt()+"</td></tr>";
        }
        html+="</table>" +
                "</body></html>";
        return html;
    }

    String generarCodigoReporteTotalSolicitado(List<EmployeeTotalDTO> datos) {
        String html= "<html>" +
                "<head><title>Total prestado por empleado</title></head>" +
                "<body>" +
                "<h1 style='text-align:center'>Total prestado por empleado</h1>" +
                "<table border='1' style='width:100%;border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='background-color:#d3d3d3'>Cedula</th>" +
                "<th style='background-color:#d3d3d3'>Nombre</th>" +
                "<th style='background-color:#d3d3d3'>Correo</th>" +
                "<th style='background-color:#d3d3d3'>Total</th>" +
                "<th style='background-color:#d3d3d3'>Cantidad de prestamos</th>" +
                "</tr>";
        for (EmployeeTotalDTO dto : datos) {
            html+="<tr><td>"+dto.code()+"</td>"+
                    "<td>"+dto.name()+"</td>"+
                    "<td>"+dto.email()+"</td>"+
                    "<td>"+dto.total()+"</td>"+
                    "<td>"+dto.loanCuantity()+"</td></tr>";
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
