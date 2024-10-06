package co.edu.uniquindio.bd1.proyectobd1.application;

import co.edu.uniquindio.bd1.proyectobd1.controllers.*;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    ModelFactoryController mfm=ModelFactoryController.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);  // Esto cerrará todos los hilos de la JVM
        });
        mfm.quemarDatos();
        mostrarLogin();
    }

    public void mostrarLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class
                    .getResource("/co/edu/uniquindio/bd1/proyectobd1/fxml/Login.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
            LoginController controlador = loader.getController();
            controlador.setAplicacion(this);
            controlador.setVentana(primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarAdministrador() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class
                    .getResource("/co/edu/uniquindio/bd1/proyectobd1/fxml/Administrador.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
            AdministradorController controlador = loader.getController();
            controlador.setAplicacion(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarTesoreria() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class
                    .getResource("/co/edu/uniquindio/bd1/proyectobd1/fxml/Tesoreria.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
            TesoreriaController controlador = loader.getController();
            controlador.setAplicacion(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEmpleado() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class
                    .getResource("/co/edu/uniquindio/bd1/proyectobd1/fxml/Empleado.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
            EmpleadoController controlador = loader.getController();
            controlador.setAplicacion(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
