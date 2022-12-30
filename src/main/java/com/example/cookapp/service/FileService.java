package com.example.cookapp.service;

import java.io.File;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile(); // $http

    File getDataFile(); // $http


}
