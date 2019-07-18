module ToDoApp {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.java;
    opens sample.Controller to javafx.fxml;
    opens sample to javafx.graphics;
}