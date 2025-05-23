package WisdomBites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ProgressBar;

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

    // Unit 1 week fields
    @FXML private TextField unit1Weeks1, unit1Weeks2, unit1Weeks3, unit1Weeks4, unit1Weeks5,
            unit1Weeks6, unit1Weeks7, unit1Weeks8, unit1Weeks9, unit1Weeks10,
            unit1Weeks11, unit1Weeks12, unit1Weeks13;

    // Unit 2 week fields
    @FXML private TextField unit2Weeks1, unit2Weeks2, unit2Weeks3, unit2Weeks4, unit2Weeks5,
            unit2Weeks6, unit2Weeks7, unit2Weeks8, unit2Weeks9, unit2Weeks10,
            unit2Weeks11, unit2Weeks12, unit2Weeks13;

    // Unit 3 week fields
    @FXML private TextField unit3Weeks1, unit3Weeks2, unit3Weeks3, unit3Weeks4, unit3Weeks5,
            unit3Weeks6, unit3Weeks7, unit3Weeks8, unit3Weeks9, unit3Weeks10,
            unit3Weeks11, unit3Weeks12, unit3Weeks13;

    // Progress bars
    @FXML private ProgressBar unit1ProgressBar;
    @FXML private ProgressBar unit2ProgressBar;
    @FXML private ProgressBar unit3ProgressBar;


    @FXML
    public void initialize() {
        for (int i = 1; i <= 13; i++) {
            addTextListener("unit1Weeks" + i);
            addTextListener("unit2Weeks" + i);
            addTextListener("unit3Weeks" + i);
        }
        updateProgressBars();
    }

    private void addTextListener(String fieldName) {
        try {
            TextField field = (TextField) getClass().getDeclaredField(fieldName).get(this);
            if (field != null) {
                field.textProperty().addListener((obs, oldVal, newVal) -> updateProgressBars());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProgressBars() {
        unit1ProgressBar.setProgress(getFilledCount("unit1Weeks") / 13.0);
        unit2ProgressBar.setProgress(getFilledCount("unit2Weeks") / 13.0);
        unit3ProgressBar.setProgress(getFilledCount("unit3Weeks") / 13.0);
    }

    private int getFilledCount(String prefix) {
        int count = 0;
        for (int i = 1; i <= 13; i++) {
            try {
                TextField field = (TextField) getClass().getDeclaredField(prefix + i).get(this);
                if (field != null && !field.getText().trim().isEmpty()) {
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @FXML
    private void handleBack() {
        SceneController.switchScene("home_page.fxml");
    }
}