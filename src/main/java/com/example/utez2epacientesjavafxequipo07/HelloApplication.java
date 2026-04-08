package com.example.utez2epacientesjavafxequipo07;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/utez2epacientesjavafxequipo07/views/principal_old.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 440);
        stage.setTitle("Biblioteca Virtual");
        stage.setScene(scene);
        stage.show();
    }
}
