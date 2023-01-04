package com.example.cookapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor   //$$$
public class Recipe {
    private  String recipeName;   //$$$ убрал модификатор final
    private  int cookingTime;     //$$$ убрал модификатор final
    private ArrayList<String> cookingStep;
    private ArrayList<Ingredient> ingredients;

    public ArrayList<String> getCookingStep() {
        return cookingStep;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
