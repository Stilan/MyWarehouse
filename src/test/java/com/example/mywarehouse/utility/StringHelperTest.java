package com.example.mywarehouse.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class StringHelperTest {

    @Test
    void getString() {
        Optional<String> str = Optional.of("Java");
        String result = StringHelper.getString(str);
        Assertions.assertEquals("Java", result);
    }
}