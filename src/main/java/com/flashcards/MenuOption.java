package com.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public abstract class MenuOption {
    @FXML
    protected void backToMenu(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/menuStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards");
        stage.setScene(scene);
        stage.show();
    }
}
