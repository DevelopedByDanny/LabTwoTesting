package com.example.assignmentTwo;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private final List<Frame> score = new ArrayList<>();

    private boolean isFrameOver = true;

    private record Frame(int firstRoll, int secondRoll) {
        private static Frame of(int newRoll, int secondRoll) {
            return new Frame(newRoll, secondRoll);
        }

    }

    public void roll(int i) {
        if (i > 10 || i < 0) throw new IllegalArgumentException();
        if (score.size() > 11) throw new RuntimeException();

        if (isFrameOver) {
            if (i == 10) {
                score.add(Frame.of(i, 0));
            } else {
                score.add(Frame.of(i, 0));
                isFrameOver = false;
            }
        } else {
            var saveFirstRoll = score.get(score.size() - 1).firstRoll;
            if (saveFirstRoll + i > 10) throw new RuntimeException();
            score.remove(score.size() - 1);
            score.add(Frame.of(saveFirstRoll, i));
            isFrameOver = true;
        }
    }

    public int score() {
        var sum = 0;
        for (int i = 0; i < score.size(); i++) {
            if (i < 10) sum += score.get(i).firstRoll + score.get(i).secondRoll;
            if (score.get(i).firstRoll == 10 && score.size() > i + 2) {
                if (score.get(i + 1).firstRoll == 10) sum += score.get(i + 1).firstRoll + score.get(i + 2).firstRoll;
                else sum += score.get(i + 1).firstRoll + score.get(i + 1).secondRoll;
            } else if (score.get(i).firstRoll == 10 && score.size() > i + 1 && i != 10) {
                sum += score.get(i + 1).firstRoll + score.get(i + 1).secondRoll;
            } else if (!(score.get(i).firstRoll == 10) && score.get(i).firstRoll + score.get(i).secondRoll == 10 && score.size() > i + 1) {
                sum += score.get(i + 1).firstRoll;
            }
        }
        return sum;
    }
}
