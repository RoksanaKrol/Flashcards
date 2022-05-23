package com.flashcards;

import java.time.LocalDate;

public class Card {
    private int id;
    private int idDeck;
    private String nameDeck = getNameDeck();
    private String front;
    private String back;
    private LocalDate lastReview;
    private LocalDate nextReview;

    public Card(int id, int idDeck, String front, String back, LocalDate lastReview, LocalDate nextReview) {
        this.id = id;
        this.idDeck = idDeck;
        this.front = front;
        this.back = back;
        this.lastReview = lastReview;
        this.nextReview = nextReview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDeck() {
        return idDeck;
    }

    public void setIdDeck(int idDeck) {
        this.idDeck = idDeck;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public LocalDate getLastReview() {
        return lastReview;
    }

    public void setLastReview(LocalDate lastReview) {
        this.lastReview = lastReview;
    }

    public LocalDate getNextReview() {
        return nextReview;
    }

    public void setNextReview(LocalDate nextReview) {
        this.nextReview = nextReview;
    }
    @Override
    public String toString() {
        return "["+id+", "+front+" | "+back+" last review "+lastReview+" next review "+nextReview+"]";
    }

    public String getNameDeck() {
        Database db = new Database("database.db");
        return db.findDeck(idDeck).getName();
    }
}
