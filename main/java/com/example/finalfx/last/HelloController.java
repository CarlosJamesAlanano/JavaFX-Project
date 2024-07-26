package com.example.finalfx.last;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPassword;
    @FXML
    private TextField etEmail;
    @FXML
    private PasswordField etPassword;
    @FXML
    private Label btnRegister;

    @FXML
    private void initialize() {
    }

    @FXML
    private void onRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Stage stage = (Stage) btnRegister.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogin() {
        String username = etEmail.getText();
        String password = etPassword.getText();
        boolean loginSuccessful = JDBCUtil.validateLogin(username, password);

        if (loginSuccessful) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
