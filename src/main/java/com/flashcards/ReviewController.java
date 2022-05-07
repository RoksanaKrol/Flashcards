package com.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewController {
    @FXML
    private Button backToMenu;
    @FXML
    private Label front;
    @FXML
    private Label back;
    @FXML
    private Button easy;
    @FXML
    private Button ok;
    @FXML
    private Button hard;
    @FXML
    private Button show;
    @FXML
    private Button review;
    @FXML
    private VBox frontBox;
    @FXML
    private VBox backBox;
    private int cardIndex = 0;

    ArrayList<Card> cards = new ArrayList<>();
    // Deck deck = new Deck(cards);

    @FXML
    protected void initialize() {
        //default cards
        cards.add(new Card("dog","pies", LocalDateTime.now(),LocalDateTime.now()));
        cards.add(new Card("cat","kot", LocalDateTime.now(),LocalDateTime.now()));
        cards.add(new Card("mouse","mysz", LocalDateTime.now(),LocalDateTime.now()));
        cards.add(new Card("fox","lis", LocalDateTime.now(),LocalDateTime.now()));
        review();
    }
    @FXML
    protected void backToMenu(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 420, 340);
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/menuStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void easy() {
        cardIndex++;
        nextCard();
    }
    @FXML
    protected void ok() {
        cardIndex++;
        nextCard();
    }
    @FXML
    protected void hard() {
        cardIndex++;
        nextCard();
    }
    @FXML
    protected void showAnswer() {
        back.setVisible(true);
        back.setManaged(true);
        easy.setVisible(true);
        easy.setManaged(true);
        ok.setVisible(true);
        ok.setManaged(true);
        hard.setVisible(true);
        hard.setManaged(true);
        show.setVisible(false);
        show.setManaged(false);
    }
    @FXML
    protected void review() {
        review.setVisible(false);
        review.setManaged(false);
        front.setVisible(true);
        front.setManaged(true);
        frontBox.setVisible(true);
        frontBox.setManaged(true);
        backBox.setVisible(true);
        backBox.setManaged(true);
        backToMenu.setVisible(false);
        backToMenu.setManaged(false);
        nextCard();
    }

    private void nextCard() {
        back.setVisible(false);
        back.setManaged(false);
        easy.setVisible(false);
        easy.setManaged(false);
        ok.setVisible(false);
        ok.setManaged(false);
        hard.setVisible(false);
        hard.setManaged(false);
        show.setVisible(true);
        show.setManaged(true);
        displayCard(cardIndex);
    }

    private void displayCard(int index) {
        if (index > -1 && index < cards.size()) {
            Card card = cards.get(index);
            front.setText(card.getFront());
            back.setText(card.getBack());
        } else {
            backBox.setVisible(false);
            show.setVisible(false);
            show.setManaged(false);
            front.setStyle("-fx-text-fill:red; -fx-font: 17 arial;");
            front.setText("All card reviewed");
            backToMenu.setVisible(true);
            backToMenu.setManaged(true);
        }
    }
}