package com.example.cookapp.service;

import com.example.cookapp.model.Recipe;

import java.nio.file.Path;
import java.util.Collection;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getTheRecipe(Integer id);

    boolean deleteTheRecipe(Integer id);

    Recipe editTheRecipe(Integer id, Recipe recipe);

    Collection<Recipe> getAllRecipe();

    // --------------------- debug -------------------------------------------------------------
    Path createListOfAllRecipes();
}
