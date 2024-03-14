package com.example.mywarehouse.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class FileNameTest {

    @Test
    void getFileName() {
        String str = "Hello";
        LocalDateTime date = LocalDateTime.now();
        String test = str + " " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy 'T' HH:mm"));
        String result = FileName.getFileName(str);
        Assertions.assertEquals(test, result);
    }
}