module com.example.finalfx.last {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires mysql.connector.j;


    opens com.example.finalfx.last to javafx.fxml;
    exports com.example.finalfx.last;
}