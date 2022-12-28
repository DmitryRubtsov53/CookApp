package com.example.cookapp.service;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();
}
