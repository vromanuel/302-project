package WisdomBites.controller;

import WisdomBites.model.Task;
import WisdomBites.model.TaskDao;
import WisdomBites.model.User;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


public class TaskController {

    // FXML elements to control!!
    @FXML
    private VBox taskListContainer;
    @FXML private TextField titleField;

    @FXML private TextArea descriptionField;
    @FXML private Button addTaskButton;
    @FXML private Button backButton;

    @FXML
    public void initialize() {
        // load every task into the view
        loadTasks();

        // enable the user to go back to the home screen
        backButton.setOnAction(e -> {
            SceneController.switchScene("home_page.fxml");

        });
    }

    @FXML
    private void handleAddTaskButton() {
        // get the text from the user's input into the fields
        String title = titleField.getText();

        String desc = descriptionField.getText();

        // you cannot have a blank title for a field, so it validates this
        if (!title.isBlank()) {

            // adds the task to the database
            TaskDao.addTask(title, desc, "F");

            // clear the title and task description so that the user may use it again
            titleField.clear();

            descriptionField.clear();

            // refresh with new task
            loadTasks();
        }
    }

    private void loadTasks() {
        // clear everything where the task is displayed and fetch all the current tasks
        taskListContainer.getChildren().clear();

        // get the current user's tasks that are not yet completed
        int userID = StateController.currentUser.getId();
        List<Task> tasks = TaskDao.getTasksByUser(userID);

        // iterate through the tasks in the list and create a box to display each task
        for (Task task : tasks) {

            // box to display tasks
            VBox taskBox = new VBox(5);
            taskBox.setStyle("-fx-background-color: #fbe9e7; -fx-padding: 10; -fx-background-radius: 8;");

            taskBox.setCursor(Cursor.HAND);

            //  horizontal box
            HBox topRow = new HBox(10);

            topRow.setAlignment(Pos.CENTER_LEFT);


            // checkbox to complete task
            CheckBox checkBox = new CheckBox();
            checkBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white;");


            // name of the task
            Label titleLabel = new Label(task.getTitle());

            titleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #3e2723;");

            // put it in the app
            topRow.getChildren().addAll(checkBox, titleLabel);

            // description of task
            Label descLabel = new Label(task.getDescription());
            descLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #5d4037;");
            descLabel.setWrapText(true);

            descLabel.setVisible(false);

            descLabel.setManaged(false);

            // shows the user the date that they created the task
            Label dateCreatedLabel = new Label("Created: " + task.getDateCreated());

            dateCreatedLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #795548;");
            dateCreatedLabel.setVisible(false);
            dateCreatedLabel.setManaged(false);


            // expand taskbox when the user clicks to show the description
            taskBox.setOnMouseClicked(event -> {

                // show description

                boolean showing = descLabel.isVisible();
                descLabel.setVisible(!showing);
                descLabel.setManaged(!showing);

                dateCreatedLabel.setVisible(!showing);

                dateCreatedLabel.setManaged(!showing);
            }

            );


            // checkbox updates the event to set the task completion to true, reloads tasks
            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) {
                    TaskDao.completeTask(task.getId());

                    loadTasks();
                }
            }
            );

            taskBox.getChildren().addAll(topRow, descLabel, dateCreatedLabel);

            taskListContainer.getChildren().add(taskBox);
        }
    }
}
