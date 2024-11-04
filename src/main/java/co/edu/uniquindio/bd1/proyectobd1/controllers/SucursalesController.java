package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.BranchEditDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.MunicipalityBranchDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SucursalesController {

    @FXML
    private TableView<String> tableMunicipio;

    @FXML
    private TableColumn<String, String> columnMunicipioNombre;

    @FXML
    private TableView<String> tableSucursales;

    @FXML
    private TableColumn<String, String> columnSucursalNombre;

    @FXML
    private TextField txtNombreSucursal;

    @FXML
    private Label lblMunicipio;

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private void initialize() {
        actualizarTablaMunicios();
    }

    private void actualizarTablaMunicios() {
        ObservableList<String> listaPrestamosProperty= FXCollections.observableList(mfm.obtenerMunicipios());
        tableMunicipio.setItems(listaPrestamosProperty);
        columnMunicipioNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    }

    @FXML
    void seleccionarMunicipio() {
        String prestamo=tableMunicipio.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            lblMunicipio.setText(prestamo);
            actualizarTablaSucursales(prestamo);
        }
    }


    boolean verificarDatosSucursales() {
        boolean sonValidos=true;
        String msj="";
        msj+= InterfazFXUtil.verificarDato(txtNombreSucursal,"","sucursal");
        if (msj.equals("") && mfm.existeSucursalEnMunicipio(new MunicipalityBranchDTO(
                lblMunicipio.getText(),
                txtNombreSucursal.getText().trim()
        ))) {
            msj+="Ya existe una sucursal con ese nombre en ese municipio";
        }
        if (!msj.equals("")) {
            sonValidos=false;
            InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, Alert.AlertType.ERROR);
        }
        return sonValidos;
    }

    void actualizarTablaSucursales(String municipio) {
        tableSucursales.getItems().clear();
        ObservableList<String> listaSucursalesProperty=
                FXCollections.observableList(mfm.obtenerSucursales(municipio));
        tableSucursales.setItems(listaSucursalesProperty);
        columnSucursalNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    }

    @FXML
    void seleccionarSucursal() {
        String sucursal=tableSucursales.getSelectionModel().getSelectedItem();
        if (sucursal != null) {
            txtNombreSucursal.setText(sucursal);
        }
    }

    @FXML
    void limpiarSucursal() {
        lblMunicipio.setText("");
        txtNombreSucursal.setText("");
        tableSucursales.getItems().clear();
    }

    @FXML
    void eliminarSucursal() {
        String sucursal=tableSucursales.getSelectionModel().getSelectedItem();
        if (sucursal != null) {
            mfm.eliminarSucursal(new MunicipalityBranchDTO(
                    lblMunicipio.getText(),
                    sucursal
            ));
            actualizarTablaSucursales(lblMunicipio.getText());
        }
    }

    @FXML
    void editarSucursal() {
        String sucursal=tableSucursales.getSelectionModel().getSelectedItem();
        if (sucursal != null) {
            if (verificarDatosSucursales()) {
                mfm.editarSucursal(new BranchEditDTO(
                        lblMunicipio.getText(),
                        sucursal,
                        txtNombreSucursal.getText()
                ));
                actualizarTablaSucursales(lblMunicipio.getText());
            }
        } else {
            InterfazFXUtil.mostrarMensaje("Sucursal no seleccionado",
                    "No ha seleccionado ningun sucursal", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void guardarSucursal() {
        if (!lblMunicipio.getText().equals("")) {
            if (verificarDatosSucursales()) {
                mfm.guardarSucursal(new MunicipalityBranchDTO(
                        lblMunicipio.getText(),
                        txtNombreSucursal.getText()
                ));
                actualizarTablaSucursales(lblMunicipio.getText());
            }
        } else {
            InterfazFXUtil.mostrarMensaje("Municipio no seleccionado",
                    "No ha seleccionado ningun municipio", Alert.AlertType.WARNING);
        }
    }

}
