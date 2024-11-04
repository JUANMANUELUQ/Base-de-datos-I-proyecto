package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.MunicipalityEditDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MunicipiosController {

    @FXML
    private TableColumn<String, String> columnMunicipioNombre;

    @FXML
    private TableView<String> tableMunicipio;

    @FXML
    private TextField txtNombreMunicipio;

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
        String municipio=tableMunicipio.getSelectionModel().getSelectedItem();
        if (municipio != null) {
            txtNombreMunicipio.setText(municipio);
        }
    }

    @FXML
    void guardarMunicipio() {
        if (verificarDatosMunicipio()) {
            mfm.guardarMunicipio(txtNombreMunicipio.getText());
            actualizarTablaMunicios();
        }
    }

    boolean verificarDatosMunicipio() {
        boolean sonValidos=true;
        String msj="";
        msj+= InterfazFXUtil.verificarDato(txtNombreMunicipio,"","municipio");
        if (msj.equals("") && mfm.existeMunicipio(txtNombreMunicipio.getText())) {
            msj+="Ya existe un municipio con ese nombre";
        }
        if (!msj.equals("")) {
            sonValidos=false;
            InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, Alert.AlertType.ERROR);
        }
        return sonValidos;
    }

    @FXML
    void editarMunicipio() {
        String municipio=tableMunicipio.getSelectionModel().getSelectedItem();
        if (municipio != null) {
            if (verificarDatosMunicipio()) {
                mfm.editarMunicipio(new MunicipalityEditDTO(
                        municipio,
                        txtNombreMunicipio.getText()
                ));
                actualizarTablaMunicios();
            }
        } else {
            InterfazFXUtil.mostrarMensaje("Municipio no seleccionado",
                    "No ha seleccionado ningun municipio para editar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarMunicipio() {
        String prestamo=tableMunicipio.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            mfm.eliminarMunicipio(prestamo);
            actualizarTablaMunicios();
        } else {
            InterfazFXUtil.mostrarMensaje("Municipio no seleccionado",
                    "No ha seleccionado ningun municipio para eliminar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void limpiarMunicipio() {
        txtNombreMunicipio.setText("");
    }

}
