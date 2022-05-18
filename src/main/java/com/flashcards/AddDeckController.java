package com.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddDeckController extends MenuOption {

    public TextField name;

    Database db = new Database("database.db");

    @FXML
    protected void save(ActionEvent event) {
        db.insertDeck(name.getText());
        name.setText("");
    }

    @FXML
    protected void back(ActionEvent event) throws IOException {
        MenuController mc = new MenuController();
        mc.deck(event);
    }
}
