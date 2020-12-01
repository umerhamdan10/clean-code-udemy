package com.d.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStringHelperTest {
    //"ABCD" => "BCD", "AACD"=> "CD", "BACD"=>"BCD", "AAAA" => "AA", "MNAA"=>"MNAA"
    //1.Red 2. Green 3.Refactor
    MyStringHelper helper = new MyStringHelper();
    @Test
    void testReplaceAInFirst2Positions() {
        assertEquals( "BCD", helper.myReplaceAInFirst2Positions("ABCD"));
        assertEquals("CD",helper.myReplaceAInFirst2Positions("AACD"));
        assertEquals("BCD",helper.myReplaceAInFirst2Positions("BACD"));
        assertEquals("AA",helper.myReplaceAInFirst2Positions("AAAA"));
        assertEquals("MNAA",helper.myReplaceAInFirst2Positions("MNAA"));
        assertEquals("",helper.myReplaceAInFirst2Positions(""));
        assertEquals("",helper.myReplaceAInFirst2Positions("A"));
        assertEquals("",helper.myReplaceAInFirst2Positions("AA"));
        assertEquals("B",helper.myReplaceAInFirst2Positions("B"));
        assertEquals("BC",helper.myReplaceAInFirst2Positions("BC"));

    }
    @Test
    void testAreFirstTowAndLastTwoCharTheSame(){
        assertFalse(helper.areFirstTowAndLastTwoCharTheSame(""));
        assertFalse(helper.areFirstTowAndLastTwoCharTheSame("A"));
        assertTrue(helper.areFirstTowAndLastTwoCharTheSame("AB"));
        assertFalse (helper.areFirstTowAndLastTwoCharTheSame("ABC"));
        assertTrue(helper.areFirstTowAndLastTwoCharTheSame("AAA"));
        assertTrue(helper.areFirstTowAndLastTwoCharTheSame("ABCAB"));
        assertFalse(helper.areFirstTowAndLastTwoCharTheSame("ABCDEBA"));


    }
}
