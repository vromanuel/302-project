package WisdomBites.controller;

import WisdomBites.model.Unit;
import WisdomBites.model.UnitDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class SyllabusController {

    @FXML private ComboBox<String> unitComboBox;
    @FXML private TextField week1Field, week2Field, week3Field, week4Field, week5Field,
            week6Field, week7Field, week8Field, week9Field, week10Field,
            week11Field, week12Field, week13Field;

    @FXML private CheckBox week1Done, week2Done, week3Done, week4Done, week5Done,
            week6Done, week7Done, week8Done, week9Done, week10Done,
            week11Done, week12Done, week13Done;

    @FXML private Button backButton;

    private final UnitDAO unitDAO = new UnitDAO();

    @FXML
    public void initialize() {
        // Predefined unit options
        unitComboBox.setItems(FXCollections.observableArrayList(
                "CAB 302: Software Development", "CAB 301: Algorithms and Complexity",
                "CAB 320: Artificial Intelligence", "CAB 201: Programming Principles"
        ));

        unitComboBox.setOnAction(e -> {
            String selectedUnit = unitComboBox.getValue();
            if (selectedUnit != null) {
                loadSyllabus(selectedUnit);
            }
        });
    }

    private void loadSyllabus(String unitName) {
        List<Unit> syllabus = unitDAO.getUnitsByName(StateController.currentUser.getId(), unitName);
        clearFields();
        for (Unit unit : syllabus) {
            int week = unit.getWeekNumber();
            getWeekField(week).setText(unit.getTaskDescription());
            getWeekCheck(week).setSelected(unit.isCompleted());
        }
    }

    @FXML
    private void handleSave() {
        String unitName = unitComboBox.getValue();
        if (unitName == null || unitName.isBlank()) {
            showAlert("Please select a unit.");
            return;
        }

        for (int i = 1; i <= 13; i++) {
            String task = getWeekField(i).getText().trim();
            boolean completed = getWeekCheck(i).isSelected();
            if (!task.isEmpty()) {
                unitDAO.saveUnit(StateController.currentUser.getId(), unitName, i, task, completed);
            }
        }

        showAlert("Syllabus saved successfully!");
    }

    @FXML
    private void handleBack() {
        SceneController.switchScene("home_page.fxml");
    }

    private void clearFields() {
        for (int i = 1; i <= 13; i++) {
            getWeekField(i).clear();
            getWeekCheck(i).setSelected(false);
        }
    }

    private TextField getWeekField(int week) {
        return switch (week) {
            case 1 -> week1Field;
            case 2 -> week2Field;
            case 3 -> week3Field;
            case 4 -> week4Field;
            case 5 -> week5Field;
            case 6 -> week6Field;
            case 7 -> week7Field;
            case 8 -> week8Field;
            case 9 -> week9Field;
            case 10 -> week10Field;
            case 11 -> week11Field;
            case 12 -> week12Field;
            case 13 -> week13Field;
            default -> throw new IllegalArgumentException("Invalid week number: " + week);
        };
    }


    private CheckBox getWeekCheck(int week) {
        return switch (week) {
            case 1 -> week1Done;
            case 2 -> week2Done;
            case 3 -> week3Done;
            case 4 -> week4Done;
            case 5 -> week5Done;
            case 6 -> week6Done;
            case 7 -> week7Done;
            case 8 -> week8Done;
            case 9 -> week9Done;
            case 10 -> week10Done;
            case 11 -> week11Done;
            case 12 -> week12Done;
            case 13 -> week13Done;
            default -> throw new IllegalArgumentException("Invalid week number: " + week);
        };
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Syllabus Manager");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
