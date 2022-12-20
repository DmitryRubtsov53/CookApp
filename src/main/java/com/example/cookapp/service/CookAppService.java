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

    public Recipe addRecipe(Recipe recipe) {
        return recipeMap.put(recipeMap.size(), recipe);
    }
    public Recipe getTheRecipe(Integer id) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.get(id);
        } else
            throw new RuntimeException("Pецепта с таким id нет.");
    }
    public void addIngredient(Ingredient ingredient) {
        //ingredient.add();
    }
    public Collection<Recipe> getAllRecipe() {
        return recipeMap.values();
    }
}
