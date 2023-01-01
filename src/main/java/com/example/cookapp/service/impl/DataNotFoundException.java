package com.example.cookapp.service.impl;

public class DataNotFoundException extends Exception {
    public DataNotFoundException() {
        super("Данных с таким ID не найдено.");
    }
}
