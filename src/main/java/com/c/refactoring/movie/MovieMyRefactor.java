package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.List;

public class MovieMyRefactor {

    String rating;

    public MovieMyRefactor(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {

        if (rating == null) {
            return false;
        }

        if (isValidARating())
            return true;

        if (isValidBRating())
            return true;


        return false;

    }

    private boolean isValidARating() {
        String firstChar = rating.substring(0, 1);
        String nextTwochar = rating.substring(1, 3);
        return firstChar.equalsIgnoreCase("A") && rating.length() == 3 && StringUtils.isNumeric(nextTwochar);
    }

    private boolean isValidBRating() {
        List<String> validBRatings = Arrays.asList("B1", "B2", "B3", "B4");
        return validBRatings.contains(rating);

    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
