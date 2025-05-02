package WisdomBites.controller;

import WisdomBites.model.Task;
import WisdomBites.model.TaskDao;
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
    @FXML
    private VBox taskListContainer;
    @FXML private TextField titleField;
    @FXML private TextArea descriptionField;

    @FXML private Button addTaskButton;
    @FXML
    public void initialize() {
        loadTasks();
    }

    @FXML
    private void handleAddTask() {
        String title = titleField.getText();
        String desc = descriptionField.getText();

        if (!title.isBlank()) {
            TaskDao.addTask(title, desc, "F");
            titleField.clear();
            descriptionField.clear();
            loadTasks(); // refresh list
        }
    }

    private void loadTasks() {
        taskListContainer.getChildren().clear();
        List<Task> tasks = TaskDao.getTasksByUser(StateController.currentUser.getId());

        for (Task task : tasks) {
            VBox taskBox = new VBox(5);
            taskBox.setStyle("-fx-background-color: #fbe9e7; -fx-padding: 10; -fx-background-radius: 8;");
            taskBox.setCursor(Cursor.HAND);

            HBox topRow = new HBox(10);
            topRow.setAlignment(Pos.CENTER_LEFT);

            CheckBox checkBox = new CheckBox();
            checkBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white;");


            Label titleLabel = new Label(task.getTitle());
            titleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #3e2723;");

            topRow.getChildren().addAll(checkBox, titleLabel);

            // Description and dateCreated, initially hidden
            Label descLabel = new Label(task.getDescription());
            descLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #5d4037;");
            descLabel.setWrapText(true);
            descLabel.setVisible(false);
            descLabel.setManaged(false);

            Label dateCreatedLabel = new Label("Created: " + task.getDateCreated());
            dateCreatedLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #795548;");
            dateCreatedLabel.setVisible(false);
            dateCreatedLabel.setManaged(false);

            // Whole box click toggles both
            taskBox.setOnMouseClicked(e -> {
                boolean showing = descLabel.isVisible();
                descLabel.setVisible(!showing);
                descLabel.setManaged(!showing);
                dateCreatedLabel.setVisible(!showing);
                dateCreatedLabel.setManaged(!showing);
            });

            // Checkbox triggers completion
            checkBox.setOnAction(e -> {
                if (checkBox.isSelected()) {
                    TaskDao.completeTask(task.getId());
                    loadTasks();
                }
            });

            taskBox.getChildren().addAll(topRow, descLabel, dateCreatedLabel);
            taskListContainer.getChildren().add(taskBox);
        }
    }
}
