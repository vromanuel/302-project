package com.example.javafxreadingdemo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloApplication extends Application {

    public CharSequence userName;
    public CharSequence password;



    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setSpacing(10.0);
        root.setFillWidth(false);



        Label label = new Label("Welcome to our project, Code Crafters!");

        VBox authentication  = new VBox();

        TextField userNameField = new TextField();
        Label userNameLabel = new Label("Username:");
        userNameField.setText("blah blah blah");

        Label passWordLabel = new Label("Password");
        TextField passWordField = new TextField();
        passWordField.setText("blah blah blah");

        Button button = new Button("login!");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                userName = userNameField.getCharacters();
                password = passWordField.getCharacters();

                System.out.println(userName);
                System.out.println(password);
            }
        });


        this.userName = userNameField.getCharacters();
        authentication.getChildren().addAll(userNameLabel, userNameField, passWordLabel, passWordField, button );

        root.getChildren().addAll(label, authentication);



        Scene scene = new Scene(root, 320, 180);
        stage.setScene(scene);
        stage.setTitle("Code Crafters!");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}