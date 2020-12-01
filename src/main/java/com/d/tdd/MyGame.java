package com.d.tdd;

public class MyGame {

    int[] rolls = new int[21];
    int rollsIndex = 0;

    public void roll(int pinsKnockedDown) {
        rolls[rollsIndex++] = pinsKnockedDown;
    }

    public int score() {
        int sum = 0;
        int rollIndex = 0;

        //5,5|1
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(rollIndex)) {

                sum += 10 + bonusForStrike(rollIndex);
                rollIndex += 1;
            } else if (isSpare(rollIndex)) {

                sum += 10 + bonusForSpare(rollIndex);
                rollIndex += 2;

            } else {
                sum += rolls[rollIndex] + rolls[rollIndex + 1];
                rollIndex += 2;
            }

        }
        return sum;
    }

    int bonusForSpare(int rollIndex) {
        return rolls[rollIndex + 2];
    }

    int bonusForStrike(int rollIndex) {
        return rolls[rollIndex + 1] + rolls[rollIndex + 2];
    }

    boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    boolean isSpare(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1] == 10;
    }

}
