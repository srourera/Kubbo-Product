package com.technicaltest.productservice.utils;

public class Utils {

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
    public static boolean isEmpty(Object value) {
        return value == null;
    }
}
