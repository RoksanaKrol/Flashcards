package com.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddDeckController extends MenuOption {

    public VBox addDeck;
    public TextField name;
    public Button save;
    public Button backToMenu;

    Database db = new Database("database.db");

    @FXML
    protected void save(ActionEvent event) {
        db.insertDeck(name.getText());
        name.setText("");
    }
}
