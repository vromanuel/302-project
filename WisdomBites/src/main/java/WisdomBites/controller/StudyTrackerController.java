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
    //
    @FXML private Button backButton;
    @FXML private Button saveButton;
    @FXML private Button loadButton;
    @FXML private Button clearAllButton;
    @FXML private Label statusLabel;

    @FXML private TextField unitName1;
    @FXML private TextField unitName2;
    @FXML private TextField unitName3;
    @FXML private TextField unitName4;


    @FXML private ProgressBar progressBar1;
    @FXML private ProgressBar progressBar2;
    @FXML private ProgressBar progressBar3;
    @FXML private ProgressBar progressBar4;

    @FXML private GridPane weeksGrid;

    private TextField[][] weekFields = new TextField[4][13];
    private String[] loadedUnitNames = new String[4];
    private UnitDAO unitDao;

    @FXML
    public void initialize() {
        unitDao = new UnitDAO();
        setupWeekFields();
        disableAllWeekFields();
    }

    private void setupWeekFields() {
        for (int unit = 0; unit < 4; unit++) {
            for (int week = 0; week < 13; week++) {
                String fieldId = "unit" + (unit+1) + "Week" + (week+1);
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

    private void disableAllWeekFields() {
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
            String unitName = nameField.getText().trim();

            if (!unitName.isEmpty()) {
                loadUnitData(unitNum, unitName);

                nameField.setDisable(true);
                nameField.setStyle("-fx-opacity: 0.9; -fx-font-weight: bold;");
            } else {
                clearUnit(unitNum);
            }
        }
        updateProgressBars();
    }

    private void loadUnitData(int unitNum, String unitName) {
        List<Unit> units = unitDao.getUnitsByName(StateController.currentUser.getId(), unitName);

        if (units.isEmpty()) {

            enableUnitWeeks(unitNum);
            loadedUnitNames[unitNum-1] = unitName;
            showStatus("New unit '" + unitName + "' ready for input", false);
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
            loadedUnitNames[unitNum-1] = unitName;
            getProgressBar(unitNum).setVisible(true);
            showStatus("Loaded unit '" + unitName + "'", false);
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
        showStatus("All fields cleared", false);
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
        loadedUnitNames[unitNum-1] = null;
    }

    private void clearAllFields() {
        unitName1.clear(); unitName1.setDisable(false); unitName1.setStyle("");
        unitName2.clear(); unitName2.setDisable(false); unitName2.setStyle("");
        unitName3.clear(); unitName3.setDisable(false); unitName3.setStyle("");
        unitName4.clear(); unitName4.setDisable(false); unitName4.setStyle("");

        for (int unit = 0; unit < 4; unit++) {
            for (int week = 0; week < 13; week++) {
                if (weekFields[unit][week] != null) {
                    weekFields[unit][week].clear();
                    weekFields[unit][week].setDisable(true);
                    weekFields[unit][week].setStyle("-fx-opacity: 0.7;");
                }
            }
            getProgressBar(unit+1).setVisible(false);
            loadedUnitNames[unit] = null;
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        try {
            for (int unitNum = 1; unitNum <= 4; unitNum++) {
                String unitName = loadedUnitNames[unitNum-1];
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
            showStatus("All changes saved!", false);
        } catch (Exception e) {
            showStatus("Error saving data!", true);
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
            getProgressBar(unit).setVisible(loadedUnitNames[unit-1] != null);
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
            case 1: return unitName1;
            case 2: return unitName2;
            case 3: return unitName3;
            case 4: return unitName4;
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

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setStyle(isError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }
}