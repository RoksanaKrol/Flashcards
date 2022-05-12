package com.flashcards;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database db = new Database("databaseTest.db");

    @Test
    void insertDeck() {
        assert(db.insertDeck("deck1"));
    }
    @Test
    void updateDeck() {
        assert(db.updateDeck(1,"deck2"));
    }
    @Test
    void selectDecks() {
        assertNotNull(db.selectDecks());
    }
    @Test
    void findDeck() {
        assertNotNull(db.findDeck(1));
    }
    @Test
    void insertCard() {
        assert(db.insertCard(1,"dog","pies", LocalDate.now(),LocalDate.now()));
    }
    @Test
    void selectCards() {
        assertNotNull(db.selectCards(1));
    }
    @Test
    void selectAllCards() {
        assertNotNull(db.selectAllCards());
    }
    @Test
    void updateCard() {
        assert(db.updateCard(1,"cat","kot", LocalDate.now(),LocalDate.now()));
    }
    @Test
    void deleteCard() {
        assert(db.deleteCard(1));
    }
    @Test
    void deleteDeck() {
        assert(db.deleteDeck(1));
    }
}