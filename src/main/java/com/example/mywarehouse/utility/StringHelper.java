package com.example.mywarehouse.utility;

import java.util.Optional;

public class StringHelper {

    private static final String STR = "String is null";

    private StringHelper() {
    }

    public static String getString(Optional<String> str) {
        return str.orElseGet(() -> STR);
    }
}
