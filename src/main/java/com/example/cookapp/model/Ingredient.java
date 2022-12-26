package com.example.cookapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private final String ingredientName;
    private final String unit;
    private final int count;

}
