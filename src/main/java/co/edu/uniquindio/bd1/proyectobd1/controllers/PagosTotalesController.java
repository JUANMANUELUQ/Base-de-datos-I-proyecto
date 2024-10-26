package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.EmployeeDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.LoanDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.PaymentDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.SearchPaymentDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

public class PagosTotalesController {

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private TableView<LoanDTO> tablePrestamo;

    @FXML
    private TableColumn<LoanDTO, String> columnNumeroPrestamoP;

    @FXML
    private TableColumn<LoanDTO, String> columnFechaSolicitud;

    @FXML
    private TableColumn<LoanDTO, String> columnMonto;

    @FXML
    private TableColumn<LoanDTO, String> columnPeriodoMeses;

    @FXML
    private TableColumn<LoanDTO, String> columnTasaInteres;

    @FXML
    private TableView<PaymentDTO> tablePagos;

    @FXML
    private TableColumn<PaymentDTO, String> columnNumeroCoutas;

    @FXML
    private TableColumn<PaymentDTO, String> columnFechaPago;

    @FXML
    private TableColumn<PaymentDTO, String> columnNumeroPrestamoC;

    @FXML
    private TableColumn<PaymentDTO, String> columnValor;


    @FXML
    void ponerDatosCuotas() {
        LoanDTO prestamo=tablePrestamo.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            List<PaymentDTO> prestamos=mfm.obtenerPrestamosEmpleado(new SearchPaymentDTO(
                    prestamo.employeeCode(),
                    prestamo.numberLoan()
            ));
            actualizarTablaCuotasPago(prestamos,prestamo.numberLoan());
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
        LoanDTO prestamo=tablePrestamo.getSelectionModel().getSelectedItem();
            if (prestamo != null) {
                EmployeeDTO empleado=mfm.obtenerEmpleadoCodigo(prestamo.employeeCode());
                String msj=organizarInfoEmpleado(empleado);
                InterfazFXUtil.mostrarMensaje("Datos del empleado",msj, Alert.AlertType.INFORMATION);
        } else {
            InterfazFXUtil.mostrarMensaje("Prestamo no seleccionado",
                    "No ha seleccionado ningun prestamo para ver la informacion del empleado", AlertType.WARNING);
        }
    }

    private void actualizarTablaPresamos() {
        tablePrestamo.getItems().clear();
        ObservableList<LoanDTO> listaPrestamosProperty= FXCollections.observableList(mfm.obtenerPagosTotales());
        tablePrestamo.setItems(listaPrestamosProperty);
        columnNumeroPrestamoP.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().numberLoan()));
        columnFechaSolicitud.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().creationDate()));
        columnMonto.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.0f",cellData.getValue().amount())));
        columnPeriodoMeses.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().periodMonths()));
        columnTasaInteres.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().interestRate()));
    }

    private void actualizarTablaCuotasPago(List<PaymentDTO> listaDTOPrestamos,Long numberLoan) {
        tablePagos.getItems().clear();
        ObservableList<PaymentDTO> listaPagosProperty= FXCollections.observableList(listaDTOPrestamos);
        tablePagos.setItems(listaPagosProperty);
        columnNumeroPrestamoC.setCellValueFactory(cellData -> new SimpleStringProperty(numberLoan+""));
        columnValor.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.0f",cellData.getValue().value())));
        columnNumeroCoutas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().paymentNumber()+""));
        columnFechaPago.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().paymentDate()+""));
    }

    public String organizarInfoEmpleado(EmployeeDTO empleado) {
        String msj="";
        msj+="Nombre de usuario: "+empleado.login()+"\n";
        msj+="Fecha de creacion: "+empleado.creationDate()+"\n";
        msj+="Nombre: "+empleado.name()+"\n";
        msj+="Correo: "+empleado.email()+"\n";
        msj+="Esta en mora: ";
        if (empleado.arrears()) {
            msj+="Si\n";
        } else  {
            msj+="No\n";
        }
        msj+="Cargo: "+empleado.position()+"\n";
        msj+="Salario: "+empleado.salary()+"\n";
        msj+="Sucursal: "+empleado.branch()+"\n";
        msj+="Municipio: "+empleado.municipality()+"\n";
        return msj;
    }

}
