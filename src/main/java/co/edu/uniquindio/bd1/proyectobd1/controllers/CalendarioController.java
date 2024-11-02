package co.edu.uniquindio.bd1.proyectobd1.controllers;

import co.edu.uniquindio.bd1.proyectobd1.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class CalendarioController {

    @FXML
    private TextField txtAnio;

    @FXML
    private Label lblMes;

    @FXML
    private GridPane gridPane;

    LocalDate fecha;
    String[] diasSemana={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo",};

    @FXML
    private void initialize() {
        LocalDate fechaActual = LocalDate.now();
        fecha = LocalDate.of(fechaActual.getYear(), fechaActual.getMonthValue(), 1);
        txtAnio.setText(String.valueOf(fecha.getYear()));
        lblMes.setText(String.valueOf(obtenerMes()));
        organizarFecha();
    }

    @FXML
    void buscarAnio() {
        if (verificarDatos()) {
            int anio = Integer.parseInt(txtAnio.getText());
            fecha=LocalDate.of(anio, fecha.getMonth(), 1);
            organizarFecha();
        }
    }

    @FXML
    void mesAnterior() {
        fecha=fecha.plusMonths(-1);
        lblMes.setText(String.valueOf(obtenerMes()));
        txtAnio.setText(String.valueOf(fecha.getYear()));
        organizarFecha();
    }

    @FXML
    void mesSiguientes() {
        fecha=fecha.plusMonths(1);
        txtAnio.setText(String.valueOf(fecha.getYear()));
        lblMes.setText(String.valueOf(obtenerMes()));
        organizarFecha();
    }

    public boolean verificarDatos() {
        boolean sonValidos=true;
        String msj="";
        msj+= InterfazFXUtil.verificarDatoNumericoEntero(txtAnio,"anio",false);
        if (!msj.equals("")) {
            sonValidos=false;
            InterfazFXUtil.mostrarMensaje("Entradas no validas", msj, Alert.AlertType.ERROR);
        }
        return sonValidos;
    }

    void organizarFecha() {
        ponerDiasSemana();
        limpiarCeldasNoUsadas();
        ponerDiasFecha();
    }

    private void agregarLabel(GridPane gridPane, int fila, int columna, String texto) {
        // Limpiar la celda antes de agregar el nuevo Label
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null &&
                GridPane.getColumnIndex(node) != null &&
                GridPane.getRowIndex(node) == fila && GridPane.getColumnIndex(node) == columna);

        // Agregar la nueva Label
        Label label = new Label(texto);
        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(label, javafx.geometry.VPos.CENTER); // Centrar el texto en la celda
        gridPane.add(label, columna, fila);
    }

    void ponerDiasSemana() {
        for (int i = 0; i < 7; i++) {
            agregarLabel(gridPane,0,i,String.valueOf(diasSemana[i]));
        }
    }

    void limpiarCeldasNoUsadas() {
        int finalCeldas=obtenerDiaSemana()-1;
        for (int i = 0; i < finalCeldas; i++) {
            agregarLabel(gridPane,1,i,"");
        }
    }

    int obtenerDiaSemana() {
        switch (fecha.getDayOfWeek()) {
            case MONDAY: return 1;
            case TUESDAY: return 2;
            case WEDNESDAY: return 3;
            case THURSDAY: return 4;
            case FRIDAY: return 5;
            case SATURDAY: return 6;
            case SUNDAY: return 7;
            default: return 0;
        }
    }

    void ponerDiasFecha() {
        int rows = gridPane.getRowCount(); // Número de filas existentes
        int dia = 1; // Número inicial
        int inicio=obtenerDiaSemana()-1;
        int diaFinalMes=fecha.lengthOfMonth();

        for (int i = 1; i < rows; i++) {
            if (i!=1) {
                inicio=0;
            }
            for (int j = inicio; j < 7; j++) {
                if (dia<=diaFinalMes) {
                    agregarLabel(gridPane,i,j,String.valueOf(dia++));
                } else {
                    agregarLabel(gridPane,i,j,"");
                }
            }
        }
    }

    String obtenerMes() {
        switch (fecha.getMonth()) {
            case JANUARY: return "Enero";
            case FEBRUARY: return "Febrero";
            case MARCH: return "Marzo";
            case APRIL: return "Abril";
            case MAY: return "Mayo";
            case JUNE: return "Junio";
            case JULY: return "Julio";
            case AUGUST: return "Agosto";
            case SEPTEMBER: return "Septiembre";
            case OCTOBER: return "Octubre";
            case NOVEMBER: return "Noviembre";
            case DECEMBER: return "Deciembre";
            default: return "";
        }
    }

}
