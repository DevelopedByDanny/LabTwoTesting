package com.example.assignmentTwo;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private final List<Integer> score = new ArrayList<>();

    public void roll(int i) {
        if (i > 10 || i < 0) throw new IllegalArgumentException();
        if (score.size() > 21) throw new RuntimeException();
        score.add(i);
    }

    public int score() {
        var sum = 0;
        var isBonus = false;
        for (int i = 0; i < score.size(); i++) {
            if (i >= 2 && i % 2 == 0 && score.get(i - 1) + score.get(i - 2) == 10) {
                sum += score.get(i);
            }

            sum += score.get(i);
        }
        return sum;
    }
}
