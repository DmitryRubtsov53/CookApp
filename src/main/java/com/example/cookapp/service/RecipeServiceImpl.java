package com.example.cookapp.service;

import com.example.cookapp.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService{
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;   // $$$
    private final FileService fileService; // $$$

    public RecipeServiceImpl(@Qualifier("recipeFileServiceImpl") FileService fileService) {  // $$$
        this.fileService = fileService;
    }

    @PostConstruct                             // $$$
    private void init() {     // $$$
        readFromFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id, recipe);
        id++;
        saveToFile();                           // $$$
        return recipe;
    }
    @Override
    public Recipe getTheRecipe(Integer id) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.get(id);
        } else
            throw new RuntimeException("Pецепта с таким id нет.");
    }
    @Override
    public boolean deleteTheRecipe(Integer id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        } else
            return false;
    }
    @Override
    public Recipe editTheRecipe(Integer id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id,recipe);
            saveToFile();                     // $$$
            return recipe;
        } else
            throw new RuntimeException ("Рецепт не найден.");
    }
    @Override
    public Collection<Recipe> getAllRecipe() {
        return recipeMap.values();
    }

//--------------------------- $$$ --------------------------------------------------
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    private void readFromFile() {
        try {
            String json = fileService.readFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
