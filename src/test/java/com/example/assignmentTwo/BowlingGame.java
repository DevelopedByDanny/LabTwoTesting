package com.example.assignmentTwo;

import net.bytebuddy.implementation.bytecode.Throw;

public class BowlingGame {

    private int score;

    public void roll(int i) {
        if (i > 10) throw new IllegalArgumentException();
        score += i;
    }

    public int score() {
        return score;
    }
}
