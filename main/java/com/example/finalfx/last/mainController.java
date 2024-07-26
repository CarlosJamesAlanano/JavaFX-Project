package com.example.finalfx.last;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class mainController {
    @FXML
    private RadioButton rbRide;

    @FXML
    private RadioButton rbLogistic;

    @FXML
    private ToggleGroup bookingTypeGroup;

    @FXML
    private Spinner<Drivers> spDrivers;

    @FXML
    private WebView wvMap;

    @FXML
    private ImageView profileImageView;

    private ObservableList<Booking> bookings = FXCollections.observableArrayList();
    private String userLocation = "Your Current Location"; // Assuming you get this from Google Maps or elsewhere

    @FXML
    public void initialize() {
        bookingTypeGroup = new ToggleGroup();
        rbRide.setToggleGroup(bookingTypeGroup);
        rbLogistic.setToggleGroup(bookingTypeGroup);

        loadGoogleMaps();
        presetDrivers();
    }

    private void loadGoogleMaps() {
        WebEngine webEngine = wvMap.getEngine();
        webEngine.setJavaScriptEnabled(true);
        String mapHtml = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    #map {
                        height: 100%;
                        width: 100%;
                    }
                    html, body {
                        height: 100%;
                        margin: 0;
                        padding: 0;
                    }
                </style>
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDJs1DvSiR3xhZgA6xHDoltaHgSCW8O3_c"></script>
                <script>
                    function initMap() {
                        var location = {lat: 14.0676, lng: 121.3225};
                        var map = new google.maps.Map(document.getElementById('map'), {
                            zoom: 12,
                            center: location
                        });
                        var marker = new google.maps.Marker({
                            position: location,
                            map: map
                        });
                    }
                    window.onload = initMap;
                </script>
            </head>
            <body>
                <div id="map"></div>
            </body>
            </html>
        """;
        webEngine.loadContent(mapHtml);
    }

    private void presetDrivers() {
        List<Drivers> drivers = List.of(
                new Drivers("John Doe", "Sedan"),
                new Drivers("Jane Smith", "SUV"),
                new Drivers("Mike Johnson", "Truck"),
                new Drivers("Nesty Bautista", "Motorcycle"),
                new Drivers("Elvin Endrenal", "Van")
        );

        SpinnerValueFactory.ListSpinnerValueFactory<Drivers> valueFactory =
                new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList(drivers));
        spDrivers.setValueFactory(valueFactory);
    }

    @FXML
    private void onBook() {
        RadioButton selectedRadioButton = (RadioButton) bookingTypeGroup.getSelectedToggle();
        String bookingType = selectedRadioButton != null ? selectedRadioButton.getText() : null;
        Drivers selectedDriver = spDrivers.getValue();

        if (bookingType == null || selectedDriver == null) {
            showAlert(Alert.AlertType.ERROR, "Booking Failed", "Please select both booking type and driver.");
            return;
        }

        Booking newBooking = new Booking(bookingType, selectedDriver, userLocation);
        bookings.add(newBooking);

        showAlert(Alert.AlertType.INFORMATION, "Booking Successful",
                "Your " + bookingType + " booking with " + selectedDriver.getName() + " at " + userLocation + " has been placed successfully.");
    }

    @FXML
    private void showBookings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookings.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            bookingsController controller = loader.getController();
            controller.setBookings(bookings);

            stage.setScene(scene);
            stage.setTitle("Your Bookings");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the bookings page.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
