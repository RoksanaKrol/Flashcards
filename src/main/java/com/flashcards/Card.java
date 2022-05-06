package com.flashcards;

import java.time.LocalDateTime;

public class Card {
    String front;
    String back;
    LocalDateTime lastReview;
    LocalDateTime nextReview;

    public Card(String front, String back, LocalDateTime lastReview, LocalDateTime nextReview) {
        this.front = front;
        this.back = back;
        this.lastReview = lastReview;
        this.nextReview = nextReview;
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

    public LocalDateTime getLastReview() {
        return lastReview;
    }

    public void setLastReview(LocalDateTime lastReview) {
        this.lastReview = lastReview;
    }

    public LocalDateTime getNextReview() {
        return nextReview;
    }

    public void setNextReview(LocalDateTime nextReview) {
        this.nextReview = nextReview;
    }
}
