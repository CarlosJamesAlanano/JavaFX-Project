package com.example.finalfx.last;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class registerController {

    @FXML
    private Button btnReg;

    @FXML
    private TextField etNewEmail;

    @FXML
    private PasswordField etNewPassword;

    @FXML
    private Label btnLogin;

    @FXML
    private void initialize() {
        // Optional: Initialize any necessary components here
    }

    @FXML
    private void onLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onRegister(ActionEvent event) {
        if (btnReg == null || etNewEmail == null || etNewPassword == null) {
            System.err.println("FXML components not initialized properly.");
            return;
        }

        String username = etNewEmail.getText();
        String password = etNewPassword.getText();
        boolean isRegistered = JDBCUtil.registerUser(username, password);

        if (isRegistered) {
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Account created successfully.");
            onLogin();
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Could not create account. Try again.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
