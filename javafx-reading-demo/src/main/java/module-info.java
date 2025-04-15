module com.example.javafxreadingdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens WisdomBites to javafx.fxml;
    exports WisdomBites;
    exports WisdomBites.controller;
    opens WisdomBites.controller to javafx.fxml;
}