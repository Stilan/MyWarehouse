package com.example.mywarehouse.utility;

import java.util.Optional;

public class StringHelper {

    private StringHelper() {
    }

    public static String getString(Optional<String> str) {
        return str.orElseGet(() -> "String is null");
    }
}
