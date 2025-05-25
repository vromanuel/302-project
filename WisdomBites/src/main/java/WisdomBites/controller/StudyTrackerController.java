package WisdomBites.controller;

import WisdomBites.model.Unit;
import WisdomBites.model.UnitDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;

public class StudyTrackerController {


    // Back button takes the user back to the home page
    @FXML private Button backButton;

    // saves the information about every code into
    @FXML private Button loadButton;

    // clears all the textboxes and
    @FXML private Button clearAllButton;

    // Text fields so the users can enter the name of the classes that they want to track
    @FXML private TextField unitCode1;
    @FXML private TextField unitCode2;
    @FXML private TextField unitCode3;
    @FXML private TextField unitCode4;

    // progress bars which show the progress of each course entered by the user (percentage of weeks which have tasks entered and saved)
    @FXML private ProgressBar progressBar1;
    @FXML private ProgressBar progressBar2;
    @FXML private ProgressBar progressBar3;
    @FXML private ProgressBar progressBar4;

    // grid to organise the weeks of each course
    @FXML private GridPane weeksGrid;

    // a two-dimensional array of text fields to hold the textfields of 4 units of 13 weeks each
    private TextField[][] weekFields = new TextField[4][13];

    // an array to store the names of the
    private String[] unitNamesEntered = new String[4];

    // The unit data access object to enable the functions to make changes to the unit table in the database
    private UnitDAO unitDao;


    @FXML
    public void initialize() {
        // Create a new instance of the unitDAO class, and set the variable declared earlier to the new object
        unitDao = new UnitDAO();

        // Adds listeners to the text fields for each week, sets the values in the weekField array to their respective week's text field
        setupWeekFields();

        // disable the text fields for each week because the user hasn't yet entered in
        disableWeekFields();
    }

    private void setupWeekFields() {
        // a loop which iterates through the units
        for (int unit = 0; unit < 4; unit++) {

            // a nested loop which iterates through the weeks
            for (int week = 0; week < 13; week++) {

                // Creates a week field with the number of the unit and week relative to in the UI
                int unitInField = unit + 1;
                int weekInField = week + 1;
                String fieldId = "unit" + unitInField + "Week" + weekInField;

                // Attempt to set an element in the weekFields array to 
                try {
                    weekFields[unit][week] = (TextField) weeksGrid.lookup("#" + fieldId);
                    if (weekFields[unit][week] != null) {
                        weekFields[unit][week].textProperty().addListener((obs, oldVal, newVal) -> {
                            updateProgressBars();
                        });

                    }

                } catch (Exception e) {
                    System.err.println("Couldn't find field: " + fieldId);
                }
            }
        }
    }

    private void disableWeekFields() {
        for (int unit = 1; unit <= 4; unit++) {
            for (int week = 1; week <= 13; week++) {
                TextField field = getWeekField(unit, week);
                if (field != null) {
                    field.setDisable(true);
                    field.setStyle("-fx-opacity: 0.7;");
                }
            }
            getProgressBar(unit).setVisible(false);
        }
    }

    @FXML
    private void handleLoad(ActionEvent event) {
        for (int unitNum = 1; unitNum <= 4; unitNum++) {
            TextField nameField = getUnitNameField(unitNum);
            // Add null check before accessing nameField
            if (nameField != null) {
                String unitName = nameField.getText().trim();

                if (!unitName.isEmpty()) {
                    loadUnitData(unitNum, unitName);

                    nameField.setDisable(true);
                    nameField.setStyle("-fx-opacity: 0.9; -fx-font-weight: bold;");
                } else {
                    clearUnit(unitNum);
                }
            }
        }
        updateProgressBars();
    }

    private void loadUnitData(int unitNum, String unitName) {
        List<Unit> units = unitDao.getUnitsByName(StateController.currentUser.getId(), unitName);

        if (units.isEmpty()) {

            enableUnitWeeks(unitNum);
            unitNamesEntered[unitNum-1] = unitName;

        } else {
            // Existing unit - load data
            for (Unit unit : units) {
                TextField weekField = getWeekField(unitNum, unit.getWeekNumber());
                if (weekField != null) {
                    weekField.setText(unit.getTaskDescription());
                    weekField.setDisable(false);
                    weekField.setStyle(unit.isCompleted() ?
                            "-fx-background-color: #e6ffe6;" : "");
                }
            }
            unitNamesEntered[unitNum-1] = unitName;
            getProgressBar(unitNum).setVisible(true);

        }
    }

    private void enableUnitWeeks(int unitNum) {
        for (int week = 1; week <= 13; week++) {
            TextField field = getWeekField(unitNum, week);
            if (field != null) {
                field.setDisable(false);
                field.setStyle("");
                field.clear();
            }
        }
        getProgressBar(unitNum).setVisible(true);
    }

    @FXML
    private void handleClearAll(ActionEvent event) {
        clearAllFields();

    }

    private void clearUnit(int unitNum) {
        TextField nameField = getUnitNameField(unitNum);
        nameField.clear();
        nameField.setDisable(false);
        nameField.setStyle("");

        for (int week = 1; week <= 13; week++) {
            TextField field = getWeekField(unitNum, week);
            if (field != null) {
                field.clear();
                field.setDisable(true);
                field.setStyle("-fx-opacity: 0.7;");
            }
        }
        getProgressBar(unitNum).setVisible(false);
        unitNamesEntered[unitNum-1] = null;
    }

    private void clearAllFields() {
        unitCode1.clear(); unitCode1.setDisable(false); unitCode1.setStyle("");
        unitCode2.clear(); unitCode2.setDisable(false); unitCode2.setStyle("");
        unitCode3.clear(); unitCode3.setDisable(false); unitCode3.setStyle("");
        unitCode4.clear(); unitCode4.setDisable(false); unitCode4.setStyle("");

        for (int unit = 0; unit < 4; unit++) {
            for (int week = 0; week < 13; week++) {
                if (weekFields[unit][week] != null) {
                    weekFields[unit][week].clear();
                    weekFields[unit][week].setDisable(true);
                    weekFields[unit][week].setStyle("-fx-opacity: 0.7;");
                }
            }
            getProgressBar(unit+1).setVisible(false);
            unitNamesEntered[unit] = null;
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        try {
            for (int unitNum = 1; unitNum <= 4; unitNum++) {
                String unitName = unitNamesEntered[unitNum-1];
                if (unitName == null || unitName.isEmpty()) continue;

                for (int week = 1; week <= 13; week++) {
                    TextField taskField = getWeekField(unitNum, week);
                    if (taskField != null) {
                        String taskText = taskField.getText().trim();
                        boolean completed = !taskText.isEmpty();

                        unitDao.saveUnit(
                                StateController.currentUser.getId(),
                                unitName,
                                week,
                                taskText,
                                completed
                        );
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneController.switchScene("home_page.fxml");
    }

    private void updateProgressBars() {
        for (int unit = 1; unit <= 4; unit++) {
            getProgressBar(unit).setProgress(getCompletionPercentage(unit));
            getProgressBar(unit).setVisible(unitNamesEntered[unit-1] != null);
        }
    }

    private double getCompletionPercentage(int unitNum) {
        if (unitNum < 1 || unitNum > 4) return 0;

        int completed = 0;
        for (int week = 1; week <= 13; week++) {
            TextField field = getWeekField(unitNum, week);
            if (field != null && !field.getText().trim().isEmpty()) {
                completed++;
            }
        }
        return completed / 13.0;
    }

    private TextField getWeekField(int unitNum, int week) {
        if (unitNum < 1 || unitNum > 4 || week < 1 || week > 13) {
            return null;
        }
        return weekFields[unitNum-1][week-1];
    }

    private TextField getUnitNameField(int unitNum) {
        switch (unitNum) {
            case 1: return unitCode1;
            case 2: return unitCode2;
            case 3: return unitCode3;
            case 4: return unitCode4;
            default: return null;
        }
    }

    private ProgressBar getProgressBar(int unitNum) {
        switch (unitNum) {
            case 1: return progressBar1;
            case 2: return progressBar2;
            case 3: return progressBar3;
            case 4: return progressBar4;
            default: return null;
        }
    }


}