package com.d.tdd;

public class MyStringHelper {
    public String myReplaceAInFirst2Positions(String str) {

        if (str.length() < 2)
            return str.replace("A", "");
        String first2Char = str.substring(0, 2);
        String remainingCharacters = str.substring(2);
        return first2Char.replaceAll("A", "") + remainingCharacters;
    }

    public boolean areFirstTowAndLastTwoCharTheSame(String str) {

        if (str.length() < 2) {
            return false;
        }

        String first2Char = str.substring(0, 2);
        String last2Chars = str.substring(str.length() - 2);

        return first2Char.equals(last2Chars);
    }
}
