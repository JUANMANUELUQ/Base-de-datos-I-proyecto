package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.dto.MunicipalityEditDTO;
import co.edu.uniquindio.bd1.proyectobd1.dto.MunicipalityInfoDTO;
import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MunicipiosController {

    @FXML
    private TableColumn<MunicipalityInfoDTO, String> columnMunicipioNombre;

    @FXML
    private TableView<MunicipalityInfoDTO> tableMunicipio;

    @FXML
    private TextField txtNombreMunicipio;

    @FXML
    private TableColumn<MunicipalityInfoDTO, String> columnPoblacion;

    @FXML
    private TextField txtPoblacion;

    @FXML
    private TextArea txtDescripcion;

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @FXML
    private void initialize() {
        actualizarTablaMunicios();
    }

    private void actualizarTablaMunicios() {
        ObservableList<MunicipalityInfoDTO> listaPrestamosProperty= FXCollections.observableList(mfm.obtenerMunicipios());
        tableMunicipio.setItems(listaPrestamosProperty);
        columnMunicipioNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        columnPoblacion.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().population()));
    }

    @FXML
    void seleccionarMunicipio() {
        MunicipalityInfoDTO municipio=tableMunicipio.getSelectionModel().getSelectedItem();
        if (municipio != null) {
            txtNombreMunicipio.setText(municipio.name());
            txtPoblacion.setText(""+municipio.population());
            txtDescripcion.setText(municipio.description());
        }
    }

    @FXML
    void guardarMunicipio() {
        if (verificarDatosMunicipio("")) {
            mfm.guardarMunicipio(new MunicipalityInfoDTO(
                    txtNombreMunicipio.getText(),
                    Long.parseLong(txtPoblacion.getText()),
                    txtDescripcion.getText()
            ));
            actualizarTablaMunicios();
        }
    }

    boolean verificarDatosMunicipio(String nombreActual) {
        boolean sonValidos=true;
        String msj="";
        msj+= InterfazFXUtil.verificarDato(txtNombreMunicipio,"","municipio");
        msj+= InterfazFXUtil.verificarDatoNumericoEntero(txtPoblacion,"poblacion",true);
        if (msj.equals("") && mfm.existeOtroMunicipio(txtNombreMunicipio.getText(),nombreActual)) {
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
        MunicipalityInfoDTO municipio=tableMunicipio.getSelectionModel().getSelectedItem();
        if (municipio != null) {
            if (verificarDatosMunicipio(municipio.name())) {
                mfm.editarMunicipio(new MunicipalityEditDTO(
                        municipio.name(),
                        txtNombreMunicipio.getText(),
                        Long.parseLong(txtPoblacion.getText()),
                        txtDescripcion.getText()
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
        MunicipalityInfoDTO prestamo=tableMunicipio.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            mfm.eliminarMunicipio(prestamo.name());
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
