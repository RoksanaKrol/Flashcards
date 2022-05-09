package com.flashcards;

public class Deck {
    private int idDeck;
    private String name;

    public Deck(int idDeck, String name) {
        this.idDeck = idDeck;
        this.name = name;
    }

    public int getIdDeck() {
        return idDeck;
    }

    public void setIdDeck(int idDeck) {
        this.idDeck = idDeck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "(Deck) ["+idDeck+"]"+name;
    }
}
