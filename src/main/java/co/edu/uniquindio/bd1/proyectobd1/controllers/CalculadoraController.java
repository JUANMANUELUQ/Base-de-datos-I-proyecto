package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CalculadoraController {

    @FXML
    private TextField txtPantalla;

    private Float operador =null;
    private String operacion="";

    /**
     * Metodo que inicializa la clase controlador
     */
    @FXML
    private void initialize() {
        txtPantalla.setEditable(false);
        txtPantalla.setStyle("-fx-background-color: #aaaaaa;");
    }

    boolean verificarEntrada() {
        try {
            Float.parseFloat(txtPantalla.getText());
            return true;
        } catch (Exception e) {
            txtPantalla.setText("");
            InterfazFXUtil.mostrarMensaje("Valor invalido","El valor ingresado no es valido", Alert.AlertType.ERROR);
            return false;
        }
    }

    @FXML
    void ac() {
        txtPantalla.setText("");
    }

    @FXML
    void siete() {
        txtPantalla.appendText("7");
    }

    @FXML
    void punto() {
        txtPantalla.appendText(".");
    }

    @FXML
    void igual() {
        if (operador !=null) {
            operar();
            operador =null;
        }
    }

    @FXML
    void dividir() {
        accionOperador("/");
    }

    @FXML
    void ocho() {
        txtPantalla.appendText("8");
    }

    @FXML
    void nueve() {
        txtPantalla.appendText("9");
    }

    @FXML
    void multiplicar() {
        accionOperador("*");
    }

    @FXML
    void cuatro() {
        txtPantalla.appendText("4");
    }

    @FXML
    void cinco() {
        txtPantalla.appendText("5");
    }

    @FXML
    void seis() {
        txtPantalla.appendText("6");
    }

    @FXML
    void restar() {
        accionOperador("-");
    }

    @FXML
    void uno() {
        txtPantalla.appendText("1");
    }

    @FXML
    void dos() {
        txtPantalla.appendText("2");
    }

    @FXML
    void tres() {
        txtPantalla.appendText("3");
    }

    @FXML
    void cero() {
        txtPantalla.appendText("0");
    }

    @FXML
    void del() {
        int tam=txtPantalla.getText().length();
        txtPantalla.deleteText(tam-1,tam);
    }

    @FXML
    void sumar() {
        accionOperador("+");
    }

    void accionOperador(String operando) {
        if (verificarEntrada()) {
            if (operador ==null) {
                operador =Float.parseFloat(txtPantalla.getText());
            } else {
                operar();
            }
            txtPantalla.setText("");
            operacion=operando;
        }

    }

    void operar() {
        switch (operacion) {
            case "+": operador +=Float.parseFloat(txtPantalla.getText()); break;
            case "-": operador -=Float.parseFloat(txtPantalla.getText()); break;
            case "*": operador *=Float.parseFloat(txtPantalla.getText()); break;
            case "/":
                if (Float.parseFloat(txtPantalla.getText())!=0) {
                    operador /=Float.parseFloat(txtPantalla.getText());
                } else {
                    InterfazFXUtil.mostrarMensaje("Error matematico","No puede dividir por 0", Alert.AlertType.ERROR);
                }
                break;
        }
        txtPantalla.setText(operador.toString());
    }

}
