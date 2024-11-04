package co.edu.uniquindio.bd1.proyectobd1.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.Map;

public class AyudasController {

    @FXML
    private TextArea txtAyuda;

    @FXML
    private ComboBox<String> comboAyudas;

    ModelFactoryController mfm=ModelFactoryController.getInstance();
    Map<String,String> ayudas;

    @FXML
    private void initialize() {
        txtAyuda.setEditable(false);
        ayudas=mfm.obtenerAyudasUsuarioSesion();
        ayudas.keySet().forEach(key->comboAyudas.getItems().add(key));
        comboAyudas.setValue("");
    }

    @FXML
    void seleccionarAyuda() {
        String msj=ayudas.get(comboAyudas.getValue());
        if (msj!=null) {
            txtAyuda.setText(msj);
        }
    }

}
