package com.example.assignmentTwo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingTest {

    @Test
    @DisplayName("Can we create a game")
    void canWeCreateAGame() {
        var game = new BowlingGame();
        assertThat(game).isInstanceOf(BowlingGame.class);
    }



}