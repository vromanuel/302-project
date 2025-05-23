
package WisdomBites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class StudyTrackerController {

    @FXML private Button backButton;

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
    @FXML private TextField unit1Week9;
    @FXML private TextField unit1Week10;
    @FXML private TextField unit1Week11;
    @FXML private TextField unit1Week12;
    @FXML private TextField unit1Week13;

    @FXML private TextField unit2Week1;
    @FXML private TextField unit2Week2;
    @FXML private TextField unit2Week3;
    @FXML private TextField unit2Week4;
    @FXML private TextField unit2Week5;
    @FXML private TextField unit2Week6;
    @FXML private TextField unit2Week7;
    @FXML private TextField unit2Week8;
    @FXML private TextField unit2Week9;
    @FXML private TextField unit2Week10;
    @FXML private TextField unit2Week11;
    @FXML private TextField unit2Week12;
    @FXML private TextField unit2Week13;

    @FXML private TextField unit3Week1;
    @FXML private TextField unit3Week2;
    @FXML private TextField unit3Week3;
    @FXML private TextField unit3Week4;
    @FXML private TextField unit3Week5;
    @FXML private TextField unit3Week6;
    @FXML private TextField unit3Week7;
    @FXML private TextField unit3Week8;
    @FXML private TextField unit3Week9;
    @FXML private TextField unit3Week10;
    @FXML private TextField unit3Week11;
    @FXML private TextField unit3Week12;
    @FXML private TextField unit3Week13;


    @FXML
    public void initialize() {
        // initialization logic (optional)
    }
    @FXML
    private void handleBack() {
        SceneController.switchScene("home_page.fxml");
    }

}