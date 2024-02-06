package com.example.assignmentTwo;

public class BowlingGame {

    private int score;

    public void roll(int i) {
        score += i;
    }

    public int score() {
        return score;
    }
}
