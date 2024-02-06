package com.example.assignmentTwo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingTest {

    private BowlingGame game = new BowlingGame();

    @Test
    @DisplayName("Can we create a game")
    void canWeCreateAGame() {
        assertThat(game).isInstanceOf(BowlingGame.class);
    }

    @Test
    @DisplayName("Roll and miss should be zero points")
    void rollAndMissShouldBeZeroPoints() {
        game.roll(0);

        assertThat(game.score()).isZero();
    }

    @Test
    @DisplayName("Roll and hit should be correct score")
    void rollAndHitShouldBeCorrectScore() {
        game.roll(1);

        assertThat(game.score()).isEqualTo(1);
    }
}