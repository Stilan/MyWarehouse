package com.example.mywarehouse.service;

import java.io.ByteArrayInputStream;

public interface FileService {

    ByteArrayInputStream getCsvProduct(String name);
}
