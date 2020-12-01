package com.d.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyGameTest {

    //0 20
    //1 20
    //1 10 2 10
    // spare 5,5 1 18

    //Red
    //Green
    //Refactor

    MyGame game = new MyGame();

    @Test
    void testAll0s() {
        int noOfTimes = 20;
        int pinsKnockDown = 0;

        rollMutipleTimes(noOfTimes, pinsKnockDown);

        assertEquals(0, game.score());
    }

    @Test
    void testAll1s() {

        rollMutipleTimes(20, 1);

        assertEquals(20, game.score());
    }

    @Test
    void testHalf1sAndHalf2s() {

        rollMutipleTimes(10, 1);
        rollMutipleTimes(10, 2);

        assertEquals(30, game.score());
    }

    @Test
    void testSpare() {

        roolASpare();
        rollMutipleTimes(18, 1);
        //5,5 | 1,1| 1,1| ......

        assertEquals(29, game.score());
    }

    @Test
    void testTwoSpare() {

        roolASpare();
        roolASpare();
        rollMutipleTimes(16, 1);
        //5,5 |5,5 | 1,1| 1,1| ......
        //15 + 11 +
        assertEquals(42, game.score());
    }

    @Test
    void testStrike() {

        game.roll(10);
        rollMutipleTimes(18, 1);
        //10 | 1,1| 1,1| ......
        //12 |

        assertEquals(30, game.score());
    }



    private void roolASpare() {
        rollMutipleTimes(2, 5);
    }


    private void rollMutipleTimes(int noOfTimes, int pinsKnockDown) {

        for (int i = 1; i <= noOfTimes; i++)
            game.roll(pinsKnockDown);
    }


}
