package com.example.cookapp.service.impl;

import com.example.cookapp.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFileServiceImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataFilePath;   // debug
    @Value("${name1.of.data.file}")
    private String dataFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFile () {         // в шпоре по-другому с параметром имени файла!!!
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFile () {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
//            throw new RuntimeException(e); - не нужен, потому что это boolean !
            e.printStackTrace(); // Специально для boolean, что бы получать инфу об ошибках!!
            return false;
        }
    }
    @Override
    public File getDataFile(){       // $http
        return new File(dataFilePath+"/"+dataFileName);
    } // $Http

    //------------------------- debug -----------------------------------------------
    @Override
    public Path createTempFile (String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
} // Class

