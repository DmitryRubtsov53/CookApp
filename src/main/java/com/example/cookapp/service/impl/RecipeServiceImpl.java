package com.example.cookapp.service.impl;

import com.example.cookapp.model.Recipe;
import com.example.cookapp.service.FileService;
import com.example.cookapp.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;
    private final FileService fileService;

    public RecipeServiceImpl(@Qualifier("recipeFileServiceImpl") FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {     // $$$
        readFromFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id, recipe);
        id++;
        saveToFile();
        return recipe;
    }
    @Override
    public Recipe getTheRecipe(Integer id) {
        try {
            if (recipeMap.containsKey(id)) {
                return recipeMap.get(id);
            } else
                throw new DataNotFoundException();
        } catch (DataNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
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
        try {
            if (recipeMap.containsKey(id)) {
                return recipeMap.put(id, recipe);
            } else
                throw new DataNotFoundException();
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
            e.printStackTrace();
        }

    }
    private void readFromFile() {
        try {
            String json = fileService.readFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
   }
// --------------------- debug -------------------------------------------------------------
    @Override
    public Path createListOfAllRecipes() {
        Path path = fileService.createTempFile("listOfAllRecipes");
        for (Recipe recipe: recipeMap.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
                writer.append(recipe.getRecipeName() + "\n");
                writer.append("----------------------------------------------------" + "\n");
                writer.append("Время приготовления: " + recipe.getCookingTime() + " мин."+ "\n");
                writer.append("Ингредиенты:"+ "\n");
                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                   writer.append((i+1) + ") " + recipe.getIngredients().get(i).getIngredientName() +
                           " - " + recipe.getIngredients().get(i).getCount() +
                           " " + recipe.getIngredients().get(i).getUnit()+ "\n");
                }
                writer.append("Инструкция приготовления:"+ "\n");
                for (int j = 0; j < recipe.getCookingStep().size(); j++) {
                    writer.append(recipe.getCookingStep().get(j)+ "\n");
                }
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();   //throw new RuntimeException(e);
            }
        }
        return path;
    }
}
