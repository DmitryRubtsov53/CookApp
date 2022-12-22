package com.example.cookapp.service;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CookAppService {
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>(); // переносим в IngredientService

    public Recipe addRecipe(Recipe recipe) {
        return recipeMap.put(recipeMap.size(), recipe);
    }
    public Recipe getTheRecipe(Integer id) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.get(id);
        } else
            throw new RuntimeException("Pецепта с таким id нет.");
    }
    public Collection<Recipe> getAllRecipe() {
        return recipeMap.values();
    }
    // Ingredients...........................................................................
//    public Ingredient getTheIngredient(Integer id) { // переносим в IngredientService
//        if (ingredientMap.containsKey(id)) {
//            return ingredientMap.get(id);
//        } else
//            throw new RuntimeException("Pецепта с таким id нет.");
//    }
//    public Ingredient addIngredient(Ingredient ingredient) {  // переносим в IngredientService
//        return ingredientMap.put(ingredientMap.size(), ingredient);
//    }

}
