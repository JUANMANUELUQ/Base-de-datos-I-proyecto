package co.edu.uniquindio.bd1.proyectobd1.utils;

import java.io.IOException;
import java.util.Optional;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import co.edu.uniquindio.bd1.proyectobd1.controllers.EmpleadoController;
import co.edu.uniquindio.bd1.proyectobd1.controllers.VentanaTabuladora;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfazFXUtil {
	
	 private static final String ALERT_CSS = "/co/edu/uniquindio/estructuras/proyecto/proyectostorify/styles/alert.css";

    /**
     * Metodo que muestra un mensaje
     * @param titulo Titulo del mensaje
     * @param encabezado Cabecera del mensaje
     * @param texto Texto principal del mensaje
     * @param alerta Alerta del mensaje
     */
    public static void mostrarMensaje(String titulo, String encabezado, String texto, AlertType alerta) {
        Alert alert = new Alert(alerta);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(texto);
        //applyAlertStyle(alert);
        alert.showAndWait();
    }

    /**
     * Metodo que muestra un mensaje
     * @param titulo Titulo del mensaje
     * @param texto Texto principal del mensaje
     * @param alerta Alerta del mensaje
     */
    public static void mostrarMensaje(String titulo, String texto, AlertType alerta) {
        Alert alert = new Alert(alerta);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(texto);
        //applyAlertStyle(alert);
        alert.showAndWait();
    }

    /**
     * Metodo que muestra un mensaje
     * @param titulo Titulo del mensaje
     * @param texto Texto principal del mensaje
     */
    public static void mostrarMensaje(String titulo, String texto) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(texto);
        //applyAlertStyle(alert);
        alert.showAndWait();
    }

    /**
     * Metodo que muestra un mensaje
     * @param texto Titulo del mensaje
     */
    public static void mostrarMensaje(String texto) {
        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("");
        alert.setHeaderText("");
        alert.setContentText(texto);
        //applyAlertStyle(alert);
        alert.showAndWait();
    }

    /**
     * Aplica el estilo CSS a la alerta.
     * @param alert La alerta a la que se le aplicará el estilo.
     */
    private static void applyAlertStyle(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        alert.getDialogPane().getStylesheets().add(InterfazFXUtil.class.getResource(ALERT_CSS).toExternalForm());
    }
	
	/**
	 * Metodo que verifica si el campo esta vacio
	 * @param campo que se quiere verificar si esta vacio
	 * @return Respuesta de que si esta vacio o no
	 */
	public static boolean estaCampoVacio(TextField campo) {
		return campo.getText() == null || campo.getText().trim().equals("");
	}
	
	/**
	 * Metodo que verifica si el campo esta vacio
	 * @param campo que se quiere verificar si esta vacio
	 * @return Respuesta de que si esta vacio o no
	 */
	public static boolean estaCampoVacio(ComboBox campo) {
		return campo.getValue() == null || ((String) campo.getValue()).trim().equals("");
	}

	public static String verificarDato(ComboBox campo,String nombreCampo) {
		String mensajeError="";
		if (estaCampoVacio(campo)) {
			mensajeError="El campo de "+nombreCampo+" no puede estar vacio";
		}
		if (!mensajeError.equals("")) {
			mensajeError+="\n";
		}
		return mensajeError;
	}

	public static String verificarDato(TextField campo,String patron,String nombreCampo) {
		String mensajeError="";
		if (estaCampoVacio(campo)) {
			mensajeError="El campo de "+nombreCampo+" no puede estar vacio";
		} else if (!patron.equals("") && !campo.getText().trim().matches(patron)){
			mensajeError="El valor del campo "+nombreCampo+" es invalido";
		}
		if (!mensajeError.equals("")) {
			mensajeError+="\n";
		}
		return mensajeError;
	}

	public static String verificarDatoNumericoReal(TextField campo,String nombreCampo,boolean positivo) {
		String mensajeError="";
		if (estaCampoVacio(campo)) {
			mensajeError="El campo de "+nombreCampo+" no puede estar vacio";
		} else {
			try {
				double numero=Double.parseDouble(campo.getText());
				if (positivo && numero<0) {
					mensajeError=mensajeError+" debe ser mayor que 0";
				}
			} catch (Exception e) {
				mensajeError=mensajeError+" debe ser un numero";
			}
		}
		return mensajeError;
	}

	public static String verificarDatoNumericoEntero(TextField campo,String nombreCampo,boolean positivo) {
		String mensajeError="";
		if (estaCampoVacio(campo)) {
			mensajeError="El campo de "+nombreCampo+" no puede estar vacio";
		} else {
			try {
				int numero=Integer.parseInt(campo.getText());
				if (positivo && numero<0) {
					mensajeError=mensajeError+" debe ser mayor que 0";
				}
			} catch (Exception e) {
				mensajeError=mensajeError+" debe ser un numero entero";
			}
		}
		return mensajeError;
	}
	
    /**
     * Bloquea la entrada de texto en un campo de texto y establece el estilo de fondo como gris.
     * @param texto El campo de texto a bloquear.
     */
	public static void bloquearEntrada(TextField texto) {
		texto.setEditable(false);
		texto.setStyle("-fx-background-color: #aaaaaa;");
		texto.setText("N/A");
	}
	
    /**
     * Desbloquea la entrada de texto en un campo de texto y restablece el estilo de fondo predeterminado.
     * @param texto El campo de texto a desbloquear.
     */
	public static void desbloquearEntrada(TextField texto) {
		texto.setEditable(true);
		texto.setStyle(null);
		texto.setText("");
	}

    /**
     * Bloquea la selección y la entrada de texto en un ComboBox estableciendo el valor predeterminado como "N/A".
     * @param comboBox El ComboBox a bloquear.
     */

	public void bloquearEntrada(ComboBox comboBox) {
		comboBox.setEditable(false);
		comboBox.setValue("N/A");
		comboBox.setDisable(true);
		
	}

	/**
     * Desbloquea la selección y la entrada de texto en un ComboBox restableciendo el valor predeterminado.
     * @param comboBox El ComboBox a desbloquear.
     */
	public static void desbloquearEntrada(ComboBox comboBox) {
		comboBox.setValue("");
		comboBox.setDisable(false);	
	}
	
	/**
	 * Muestra un mensaje que muestra una ventana para confirmar algo
	 * @param titulo Titulo de la ventana
	 * @param texto Texto principal de la ventana
	 * @return Si el usuario acepto o no la confirmacion
	 */
	public static boolean mostrarConfirmacion(String titulo, String texto) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle(titulo);
	    alert.setHeaderText(titulo);
	    alert.setContentText(texto);
	    //applyAlertStyle(alert);
	    Optional<ButtonType> resultado = alert.showAndWait();

	    return resultado.isPresent() && resultado.get() == ButtonType.OK;
	}

	/**
	 * Carga una nueva ventana en el panel principal.
	 *
	 * @param ruta   la ruta del archivo FXML que se va a cargar
	 * @param aClass
	 */
	public static void cargarVentana(String ruta, AnchorPane panel, Class<? extends VentanaTabuladora> aClass) {

		try {
			// Cargar el archivo FXML
			FXMLLoader loader = new FXMLLoader(aClass.getResource(ruta));
			Parent nuevoContenido = loader.load();

			// Agregar el nuevo contenido al AnchorPane
			panel.getChildren().setAll(nuevoContenido);
		} catch (IOException e) {
			e.printStackTrace();
			// Manejar la excepción adecuadamente
		}
	}

}
