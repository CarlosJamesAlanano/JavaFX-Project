package com.example.finalfx.last;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class bookingsController {
    @FXML
    private ListView<Booking> lvBookings;

    public void setBookings(ObservableList<Booking> bookings) {
        lvBookings.setItems(bookings);
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) lvBookings.getScene().getWindow();
        stage.close();
    }
}
