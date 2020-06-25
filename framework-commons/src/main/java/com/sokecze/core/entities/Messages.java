package com.sokecze.core.entities;

public class Messages {
    private static final String WRONG_ACTIVITY = "Expected activity is '%s' but was '%s'";
    private static final String ELEMENT_VISIBLE = "Element '%s' was visible while should not";

    public static String getActivityErrorMessage(String expected, String actual) {
        return String.format(WRONG_ACTIVITY, expected, actual);
    }

    public static String getElementVisibleErrorMessage(String element) {
        return String.format(ELEMENT_VISIBLE, element);
    }
}

