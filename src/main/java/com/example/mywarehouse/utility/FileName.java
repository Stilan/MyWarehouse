package com.example.mywarehouse.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileName {

    private FileName() {

    }

    public static String getFileName(String name) {
        LocalDateTime date = LocalDateTime.now();
        return name + " " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy 'T' HH:mm"));
    }
}
