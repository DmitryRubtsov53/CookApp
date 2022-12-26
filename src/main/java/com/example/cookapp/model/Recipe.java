package com.example.cookapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class Recipe {
    private final String recipeName;
    private final int cookingTime;
    private ArrayList<String> cookingStep;
    private ArrayList<Ingredient> ingredients;

}
