package com.example.assignmentTwo;

import net.bytebuddy.implementation.bytecode.Throw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BowlingGame {

    private final List<Integer> score = new ArrayList<>();

    public void roll(int i) {
        if (i > 10 || i < 0) throw new IllegalArgumentException();
        if (score.size() > 21) throw new RuntimeException();
        score.add(i);
    }

    public int score() {
        var sum= 0;
        for (Integer integer : score) {
            sum += integer;
        }
        return sum;
    }
}
