package com.example.assignmentTwo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingTest {

    private BowlingGame game;

    @BeforeEach
    void setup() {
        game = new BowlingGame();
    }

    static Stream<Arguments> gameScenarios() {
        return Stream.of(
                Arguments.of(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0),
                Arguments.of(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 20),
                Arguments.of(new int[]{10, 3, 7, 6, 1, 10, 10, 10, 2, 8, 9, 0, 7, 3, 10, 10, 10}, 193),
                Arguments.of(new int[]{10, 3, 7, 6, 1, 10, 10, 10, 2, 8, 9, 0, 7, 3, 10, 5, 5}, 183),
                Arguments.of(new int[]{10, 3, 7, 6, 1, 10, 10, 10, 2, 8, 9, 0, 7, 3, 8, 2, 10}, 181),
                Arguments.of(new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, 300)
        );
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
        for (int i = 0; i < 9; i++) {
            game.roll(1);
            game.roll(1);
        }
        game.roll(10);
        game.roll(10);
        game.roll(10);

        assertThrows(RuntimeException.class, () -> game.roll(1));
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

    @Test
    @DisplayName("If you strike the bonus gets added")
    void ifYouStrikeTheBonusGetsAdded() {
        game.roll(10);
        game.roll(1);
        game.roll(1);
        game.roll(1);
        game.roll(5);

        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    @DisplayName("if you strike and then spare it should calculate correct bonus")
    void ifYouStrikeAndThenSpareItShouldCalculateCorrectBonus() {
        game.roll(10);
        game.roll(2);
        game.roll(8);
        game.roll(1);
        game.roll(1);

        assertThat(game.score()).isEqualTo(33);
    }

    @Test
    @DisplayName("error should be thrown if first plus second roll is more than ten")
    void errorShouldBeThrownIfFirstPlusSecondRollIsMoreThanTen() {
        game.roll(5);

        assertThrows(RuntimeException.class, () -> game.roll(6));
    }

    @ParameterizedTest
    @MethodSource("gameScenarios")
    void testCompleteGame(int[] rolls, int expectedScore) {
        for (int i : rolls) {
            game.roll(i);
        }
        assertThat(game.score()).isEqualTo(expectedScore);
    }
}