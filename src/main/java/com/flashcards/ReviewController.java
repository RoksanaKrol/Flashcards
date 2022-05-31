package com.flashcards;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReviewController extends MenuOption {
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
    private VBox frontBox;
    @FXML
    private VBox backBox;
    private int cardIndex;

    Database database = new Database("database.db");
    ArrayList<Card> cards;

    @FXML
    protected void initialize() {
        cards = database.selectCardsToReview(DeckController.getIdDeck());
        cardIndex = 0;
        review();
    }
    @FXML
    protected void easy() {
        setNextReviewDate(4);
        cardIndex++;
        nextCard();
    }
    @FXML
    protected void ok() {
        setNextReviewDate(1);
        cardIndex++;
        nextCard();
    }
    @FXML
    protected void hard() {
        setNextReviewDate(0);
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
        displayCard();
    }

    private void displayCard() {
        if (cardIndex > -1 && cardIndex < cards.size()) {
            Card card = cards.get(cardIndex);
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
    private void setNextReviewDate(int inHowManyDays) {
        if (cardIndex > -1 && cardIndex < cards.size()) {
            Card card = database.findCard(cards.get(cardIndex).getId());
            database.updateCard(card.getId(), card.getIdDeck(), card.getFront(), card.getBack(), LocalDate.now(), LocalDate.ofYearDay(card.getNextReview().getYear(), LocalDate.now().getDayOfYear()+inHowManyDays));
        }
    }
}
