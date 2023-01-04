package com.example.cookapp.service;

import java.io.File;
import java.nio.file.Path;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
    // ---------------------- Http ----
    File getDataFile();

    //---------------------- debug ----
    Path createTempFile(String suffix);
}
