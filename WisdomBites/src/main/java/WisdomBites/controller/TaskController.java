package WisdomBites.controller;

import WisdomBites.model.Task;
import WisdomBites.model.TaskDao;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

        assert tasks != null;
        for (Task task : tasks) {
            // Container for one task (title + buttons + expandable description)
            VBox taskBox = new VBox(5);
            taskBox.setStyle("-fx-background-color: #fff3e0; -fx-padding: 10; -fx-background-radius: 10;");

            // Top row: title and buttons
            HBox topRow = new HBox(10);
            topRow.setAlignment(Pos.CENTER_LEFT);

            Label titleLabel = new Label(task.getTitle());
            titleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #5a3921;");

            Button expandBtn = new Button("▼"); // Down arrow
            Button completeBtn = new Button("Complete");
            Button deleteBtn = new Button("Delete");

            Label dateCreatedLabel = new Label(task.getDateCreated());
            dateCreatedLabel.setAlignment(Pos.CENTER_RIGHT);

            topRow.getChildren().addAll(titleLabel, expandBtn, completeBtn, deleteBtn, dateCreatedLabel);

            // Description label (initially hidden)
            Label descLabel = new Label(task.getDescription());
            descLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7b5e3e;");
            descLabel.setWrapText(true);
            descLabel.setVisible(false);
            descLabel.setManaged(false); // Prevent it from occupying space when hidden

            // Toggle description on expandBtn click
            expandBtn.setOnAction(e -> {
                boolean showing = descLabel.isVisible();
                descLabel.setVisible(!showing);
                descLabel.setManaged(!showing);
                expandBtn.setText(showing ? "▼" : "▲");
            });

            completeBtn.setOnAction(e -> {
                TaskDao.completeTask(task.getId());

                loadTasks();
            });

            taskBox.getChildren().addAll(topRow, descLabel);
            taskListContainer.getChildren().add(taskBox);
        }
    }
}
