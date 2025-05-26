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
        for (int unit = 0; unit < 4; unit += 1) {

            // a nested loop which iterates through the weeks
            for (int week = 0; week < 13; week += 1) {



                // Creates a week field with the number of the unit and week relative to in the UI
                int unitInField = unit + 1;
                int weekInField = week + 1;

                String fieldId = "unit" + unitInField + "Week" + weekInField;

                // Attempt to set an element in the weekFields array to the field in weeksGrid with fieldID as the ID
                try {
                    weekFields[unit][week] = (TextField) weeksGrid.lookup("#" + fieldId);

                    // If the text field is filled, the progress bar will be updated to be fuller
                    if (weekFields[unit][week] != null) {

                        weekFields[unit][week].textProperty().addListener((observable, previousValue, currentValue) -> {
                            updateProgressBars();

                        });

                    }
                // Throw an error if the text field cannot be found

                } catch (Exception e) {
                    System.err.println("Couldn't find field: " + fieldId);
                }
            }
        }
    }


    private void disableWeekFields() {
        // If the user has not typed in a unit and pressed load, they may not update the value, as you cannot update a
        // week for a

        // iterates through units 1 through 4
        for (int unit = 1; unit <= 4; unit += 1) {

            // iterate weeks one through 13
            for (int week = 1; week <= 13; week += 1) {


                TextField field = getWeekField(unit, week);

                // Check if the field does not equal true in case of null pointer exception
                if (field != null) {
                    // restrict the user from entering data into the assignment
                    field.setDisable(true);


                    field.setStyle("-fx-opacity:0.7;");
                }
            }

            // a cool bar
            ProgressBar coolBar = getProgressBar(unit);

            // Make sure no null pointer exception!!

            if (coolBar != null) {
                coolBar.setVisible(false);
            }
        }
    }

    @FXML
    private void handleLoad(ActionEvent event) {

        // iterate through units
        for (int unitNum = 1; unitNum <= 4; unitNum += 1) {

            TextField nameField = getUnitNameField(unitNum);
            // check that nameField is not null to avoid null pointer
            if (nameField != null) {

                // get the text from the text field, store in variable
                String unitName = nameField.getText().trim();

                // if the text field is not empty, load the data for the unit
                if (!unitName.isEmpty()) {
                    loadUnitData(unitNum, unitName);

                    // disable the user from being able to type in the unit that's been loaded
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

        // get the user's request unit by its name
        List<Unit> units = unitDao.getUnitsByName(StateController.currentUser.getId(), unitName);

        // if the unit that the user entered does not yet exist,
        if (units.isEmpty()) {

            // The user can then modify the weeks for the unit, the state of unitNamesEntered is updated to reflect this
            enableUnitWeeks(unitNum);

            int unitIndex = unitNum - 1;
            unitNamesEntered[unitIndex] = unitName;

        } else {

            // iterate through the units which are already in the db
            for (Unit unit : units) {
                // get the text field that corresponds to the week
                TextField weekField = getWeekField(unitNum, unit.getWeekNumber());

                // If the weekField exists, the description for the task is displayed to the user in the textbox,
                if (weekField != null) {

                    weekField.setText(unit.getTaskDescription());
                    weekField.setDisable(false);

                    weekField.setStyle(unit.isCompleted() ?
                            "-fx-background-color: #e6ffe6;" : "");
                }
            }


            unitNamesEntered[unitNum - 1] = unitName;

            ProgressBar coolProgressBar = getProgressBar(unitNum);
            coolProgressBar.setVisible(true);

        }
    }

    private void enableUnitWeeks(int unitNum) {
        // Function that enables the week fields under a specific unit

        // iterate through the weeks of the unit
        for (int week = 1; week <= 13; week += 1) {

            // get the specific text fields and if the field is not null,
            TextField field = getWeekField(unitNum, week);

            // un-disable the field, remove the style that darkens it
            if (field != null) {
                field.setDisable(false);
                field.setStyle("");
                field.clear();
            }

        }

        ProgressBar coolBar = getProgressBar(unitNum);
        if(coolBar != null) {
            coolBar.setVisible(true);
        }

    }

    @FXML
    private void handleClearAll(ActionEvent event) {
        // Clears all the units and weeks, getting ready for another use
        clearAllFields();
    }

    private void clearUnit(int unitNum) {
        // clear the unit text field
        TextField nameField = getUnitNameField(unitNum);
        nameField.clear();
        nameField.setDisable(false);
        nameField.setStyle("");

        // iterate through the unit's weeks
        for (int week = 1; week <= 13; week += 1) {

            // get the specific week field, disable all the weeks
            TextField field = getWeekField(unitNum, week);
            if (field != null) {
                field.clear();
                field.setDisable(true);
                field.setStyle("-fx-opacity: 0.7;");
            }

        }

        ProgressBar coolBar = getProgressBar(unitNum);

        coolBar.setVisible(false);

        int unitIndex = unitNum - 1;
        unitNamesEntered[unitIndex] = null;
    }

    private void clearAllFields() {
        // clear every single text box which has a unit in it
        unitCode1.clear();
        unitCode1.setDisable(false);
        unitCode1.setStyle("");

        unitCode2.clear();
        unitCode2.setDisable(false);
        unitCode2.setStyle("");

        unitCode3.clear();
        unitCode3.setDisable(false);
        unitCode3.setStyle("");

        unitCode4.clear();
        unitCode4.setDisable(false);
        unitCode4.setStyle("");

        // iterate through units
        for (int unit = 0; unit < 4; unit += 1) {

            // iterate through weeks
            for (int week = 0; week < 13; week += 1) {

                // disable and clear the fields for the weeks
                if (weekFields[unit][week] != null) {
                    weekFields[unit][week].clear();

                    weekFields[unit][week].setDisable(true);
                    weekFields[unit][week].setStyle("-fx-opacity: 0.7;");
                }

            }

            // remove the progbars
            int unitNum = unit + 1;
            ProgressBar progBar = getProgressBar(unitNum);
            progBar.setVisible(false);
            unitNamesEntered[unit] = null;
        }

    }

    @FXML
    private void handleSave(ActionEvent event) {


        try {
            // Iterate through units
            for (int unitNum = 1; unitNum <= 4; unitNum += 1) {

                int unitIndex = unitNum - 1;
                String unitName = unitNamesEntered[unitIndex];

                // If the unit name is null, don't do go to the next unit
                if (unitName == null || unitName.isEmpty()) continue;

                // iterate through the weeks
                for (int week = 1; week <= 13; week += 1) {

                    //gets the taskField and stores it in a variable
                    TextField taskField = getWeekField(unitNum, week);

                    // if the field of the task is not null get the text, and save the task in the database
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
        // go back to home!!
        SceneController.switchScene("home_page.fxml");
    }

    private void updateProgressBars() {
        // iterate through the units
        for (int unit = 1; unit <= 4; unit+= 1) {
            ProgressBar theBar = getProgressBar(unit);

            theBar.setProgress(getCompletionPercentage(unit));

            int unitIndex = unit -1;
            theBar.setVisible(unitNamesEntered[unitIndex] != null);
        }
    }

    private double getCompletionPercentage(int unitNum) {
        // if the unit is not in the 4, return 0
        if (unitNum < 1 || unitNum > 4) return 0;

        // initialise a variable to store the completed amount of weeks
        int completed = 0;

        // iterate through the weeks
        for (int week = 1; week <= 13; week += 1) {

            // if there's something in the text field, add to completed
            TextField field = getWeekField(unitNum, week);

            if (field != null && !field.getText().trim().isEmpty()) {

                completed += 1;
            }
        }
        // divide the number of weeks by 13 to get percentage
        return completed / 13.0;
    }

    private TextField getWeekField(int unitNum, int week) {
        if (unitNum < 1 || unitNum > 4 || week < 1 || week > 13) {
            return null;

        }

        return weekFields[unitNum-1][week-1];
    }

    private TextField getUnitNameField(int unitNum) {

        // Gets the unit code label from the unit number
        switch (unitNum) {
            case 1: return unitCode1;
            case 2: return unitCode2;
            case 3: return unitCode3;
            case 4: return unitCode4;
            default: return null;
        }
    }

    private ProgressBar getProgressBar(int unitNum) {

        // gets the progress bar from the unit its based off of
        switch (unitNum) {
            case 1: return progressBar1;
            case 2: return progressBar2;
            case 3: return progressBar3;
            case 4: return progressBar4;
            default: return null;
        }
    }


}