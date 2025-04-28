module com.example.javafxreadingdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;



    opens WisdomBites to javafx.fxml;
    exports WisdomBites;
    exports WisdomBites.controller;
    opens WisdomBites.controller to javafx.fxml;
    exports WisdomBites.Main;
    opens WisdomBites.Main to javafx.fxml;
    exports WisdomBites.model;
    opens WisdomBites.model to javafx.fxml;

}