package com.example.cookapp.service;

import com.example.cookapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {
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
    public boolean deleteTheRecipe(Integer id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        } else
            return false;
    }
    public Recipe editTheRecipe(Integer id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.put(id,recipe);
        } else
            throw new RuntimeException ("Рецепт не найден.");
    }

    public Collection<Recipe> getAllRecipe() {
        return recipeMap.values();
    }

}
