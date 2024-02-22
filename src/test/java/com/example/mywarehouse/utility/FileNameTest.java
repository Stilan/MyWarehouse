package com.example.mywarehouse.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//"dd.MM.yyyy 'T' HH:mm"
class FileNameTest {

    @Test
    void getFileName() {
        String str = "Hello";
        String result = FileName.getFileName(str);
        Assertions.assertEquals("Hello 12.02.2024 T 22:14", result);
    }
}