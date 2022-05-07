package com.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuController {
    public Button review;
    public VBox menuBox;
    @FXML
    private Label menuLabel;
    Stage stage;
    Scene scene;
    Parent root;

    public void initialize() {
        VBox.setMargin(menuBox, new Insets(10, 10, 30, 10));
    }
    public void review(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("review.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 420, 340);
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/reviewStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards - review");
        stage.setScene(scene);
        stage.show();
    }
}
