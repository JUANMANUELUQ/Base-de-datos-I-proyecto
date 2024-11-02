package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        if (!lblMunicipio.getText().equals("")) {

        } else {
            InterfazFXUtil.mostrarMensaje("Municipio no seleccionado",
                    "No ha seleccionado ningun municipio", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void totalSucursal() {
        if (!lblSucursal.getText().equals("")) {

        } else {
            InterfazFXUtil.mostrarMensaje("Sucursal no seleccionada",
                    "No ha seleccionado ninguna sucursal", Alert.AlertType.WARNING);
        }
    }

    void actualizarTablaSucursales(String municipio) {
        tableSucursales.getItems().clear();
        ObservableList<String> listaSucursalesProperty=
                FXCollections.observableList(mfm.obtenerSucursales(municipio));
        tableSucursales.setItems(listaSucursalesProperty);
        columnSucursalNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    }
	

}
