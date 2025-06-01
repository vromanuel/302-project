package WisdomBites.controller;

import WisdomBites.model.Unit;
import WisdomBites.model.UnitDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.stream.Collectors;

public class PersonalisedMsg {

    @FXML private TextArea predictionBox;
    @FXML private ImageView cookieImage;
    @FXML private Button backButton;
    @FXML private TextField nameField;
    @FXML private TextField subjectField;

    private final UnitDAO unitDAO = new UnitDAO();

    @FXML
    public void handleGenerate() {
        String name = nameField.getText().isBlank() ? "Student" : nameField.getText();
        String subject = subjectField.getText().isBlank() ? "your subject" : subjectField.getText();
        int streakDays = 4;

        new Thread(() -> {
            // Get syllabus data from DB
            List<Unit> units = unitDAO.getUnitsByName(StateController.currentUser.getId(), subject);
            int weeksStudied = (int) units.stream().filter(Unit::isCompleted).count();
            int weeksLeft = 13 - weeksStudied;
            List<String> topicsLeft = units.stream()
                    .filter(u -> !u.isCompleted())
                    .map(Unit::getTaskDescription)
                    .filter(desc -> desc != null && !desc.isBlank())
                    .collect(java.util.stream.Collectors.toList());

            // Get AI messages
            String welcome = PMsgController.generateWelcomeMessage(name, subject, streakDays);
            String prediction = PMsgController.generatePrediction(name, subject, weeksStudied, weeksLeft, topicsLeft);

            String finalMessage = welcome + "\n\n" + prediction;

            Platform.runLater(() -> predictionBox.setText(finalMessage));
        }).start();
    }

    @FXML
    private void handleBack() {
        SceneController.switchScene("home_page.fxml");
    }

    @FXML
    public void initialize() {
    }
}

