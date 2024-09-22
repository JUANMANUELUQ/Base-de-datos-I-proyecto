package co.edu.uniquindio.bd1.proyectobd1.application;

import co.edu.uniquindio.bd1.proyectobd1.controllers.ModelFactoryController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class pruebaMain extends Application {

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    @Override
    public void start(Stage primaryStage) {
        // Crear el TextField
        TextField textField = new TextField();
        textField.setPromptText("Escribe algo aquí");

        // Crear el botón
        Button boton = new Button("Presiona aquí");

        // Asignar el evento al botón
        boton.setOnAction(event -> ejecutarMetodo(textField.getText()));

        // Crear un layout (en este caso un VBox) y añadir los componentes
        VBox layout = new VBox(10); // Espacio de 10px entre elementos
        layout.getChildren().addAll(textField, boton);

        // Crear la escena con el layout
        Scene scene = new Scene(layout, 300, 200);

        // Configurar el escenario (ventana)
        primaryStage.setTitle("Ventana con TextField y Botón");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método que se ejecuta al presionar el botón
    public void ejecutarMetodo(String texto) {
        mfm.guardarEntidad(texto);
        System.out.println("El texto ingresado es: " + texto);
    }

    public static void main(String[] args) {
        launch(args); // Iniciar la aplicación JavaFX
    }
}
