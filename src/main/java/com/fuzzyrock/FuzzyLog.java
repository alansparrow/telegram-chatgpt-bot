package com.fuzzyrock;

public class FuzzyLog {
    public static <T> void log(String tag, T msg) {
        System.out.println(tag + ": " + msg);
    }
}
