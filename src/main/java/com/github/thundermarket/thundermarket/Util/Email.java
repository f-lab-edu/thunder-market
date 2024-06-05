package com.github.thundermarket.thundermarket.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    public static final String NOT_VALID_EMAIL_MESSAGE = "not valid email";
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValid(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

