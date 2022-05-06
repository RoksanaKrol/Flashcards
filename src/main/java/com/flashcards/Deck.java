package com.flashcards;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck;

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
