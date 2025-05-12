package WisdomBites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class StudyTrackerController {

    // Daily Check-In checkboxes
    @FXML private CheckBox mondayCheck;
    @FXML private CheckBox tuesdayCheck;
    @FXML private CheckBox wednesdayCheck;
    @FXML private CheckBox thursdayCheck;
    @FXML private CheckBox fridayCheck;

    // unit code input fields
    @FXML private TextField unitCode1;
    @FXML private TextField unitCode2;
    @FXML private TextField unitCode3;

    // individual week fields for each unit (manually linked to FXML)
    @FXML private TextField unit1Week1;
    @FXML private TextField unit1Week2;
    @FXML private TextField unit1Week3;
    @FXML private TextField unit1Week4;
    @FXML private TextField unit1Week5;
    @FXML private TextField unit1Week6;
    @FXML private TextField unit1Week7;
    @FXML private TextField unit1Week8;

    @FXML
    public void initialize() {
        // initialization logic (optional)
    }
}
