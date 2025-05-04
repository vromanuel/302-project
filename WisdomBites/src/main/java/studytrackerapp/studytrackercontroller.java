package studytrackerapp;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class studytrackercontroller {

    // Daily Check-In 체크박스
    @FXML private CheckBox mondayCheck;
    @FXML private CheckBox tuesdayCheck;
    @FXML private CheckBox wednesdayCheck;
    @FXML private CheckBox thursdayCheck;
    @FXML private CheckBox fridayCheck;

    // 유닛 코드 입력 필드 (학기마다 변경 가능)
    @FXML private TextField unitCode1;
    @FXML private TextField unitCode2;
    @FXML private TextField unitCode3;

    // 할 일 입력 필드 (각 유닛별로 8주)
    @FXML private TextField[] unit1Weeks = new TextField[8];
    @FXML private TextField[] unit2Weeks = new TextField[8];
    @FXML private TextField[] unit3Weeks = new TextField[8];

    @FXML
    public void initialize() {
        // 필요한 추가 초기화 코드 작성 가능
    }
}
