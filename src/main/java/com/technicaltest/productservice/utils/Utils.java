package com.technicaltest.productservice.utils;

public class Utils {

    public static boolean notEmpty(String value) {
        return value != null && !value.isEmpty();
    }
    public static boolean notEmpty(Object value) {
        return value != null;
    }
}
