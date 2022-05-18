package com.flashcards;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.time.LocalDate;

public class AddFlashcardController {
    public TextArea front;
    public TextArea back;
    public HBox deck;
    Database db = new Database("database.db");

    @FXML
    ChoiceBox<Deck> cb;
    @FXML
    protected void initialize() {
        cb = new ChoiceBox<>(FXCollections.observableArrayList(
                db.selectDecks())
        );
        deck.getChildren().add(cb);
    }

    @FXML
    protected void save(ActionEvent event) {
        Deck deck;
        if (cb.getValue() == null) {
            deck = db.selectDecks().get(0);
        } else {
            deck = cb.getValue();
        }
        db.insertCard(deck.getIdDeck(),front.getText(),back.getText(), LocalDate.now(),LocalDate.now());
        front.setText("");
        back.setText("");
    }
    @FXML
    protected void back(ActionEvent event) throws IOException {
        MenuController mc = new MenuController();
        mc.flashcards(event);
    }
}
