package com.example.finalfx.last;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(root.load());
        System.setProperty("java.library.path", "C:\\Users\\rbcar\\IdeaProjects\\Last\\src\\main\\resources\\com\\example\\finalfx\\last");

        stage.setTitle("Chauffeur");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
