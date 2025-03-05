package com.puig;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar la vista principal
        Parent root = FXMLLoader.load(getClass().getResource("/com/puig/views/main.fxml"));

        // Configurar la escena
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/puig/styles.css").toExternalForm());

        // Configurar la ventana principal
        primaryStage.setTitle("Cantina App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}