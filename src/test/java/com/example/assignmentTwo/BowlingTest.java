package com.example.assignmentTwo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingTest {

    private BowlingGame game;

    @BeforeEach
    void setup(){
        game = new BowlingGame();
    }
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

    @Test
    @DisplayName("Roll two balls should give correct score")
    void rollTwoBallsShouldGiveCorrectScore() {
        game.roll(1);
        game.roll(1);

        assertThat(game.score()).isEqualTo(2);
    }

    @Test
    @DisplayName("A roll can never be more than ten")
    void aRollCanNeverBeMoreThanTen() {

        assertThrows(IllegalArgumentException.class, () -> game.roll(11));

    }

    @Test
    @DisplayName("A roll can not be negative")
    void aRollCanNotBeNegative() {

        assertThrows(IllegalArgumentException.class, () -> game.roll(-1));
    }

   @Test
   @DisplayName("A game of Bowling is never more than 21 rolls")
   void aGameOfBowlingIsNeverMoreThan21Rolls() {
       for (int i = 0; i < 22; i++) {
           game.roll(10);
       }
       assertThrows(RuntimeException.class, ()-> game.roll(10));
   }

   @Test
   @DisplayName("If you make a spare the bonus gets added")
   void ifYouMakeASpareTheBonusGetsAdded() {
       game.roll(2);
       game.roll(8);
       game.roll(1);
       game.roll(1);

       assertThat(game.score()).isEqualTo(13);

   }

}