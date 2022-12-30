package com.example.cookapp.service.impl;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.service.FileService;
import com.example.cookapp.service.IngredientService;
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
public class IngredientServiceImpl implements IngredientService {

    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;   // $$$
    private final FileService fileService; // $$$

    public IngredientServiceImpl (@Qualifier("ingredientFileServiceImpl") FileService fileService) {  // $$$
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {     // $$$
        readFromFile();
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
        id++;
        saveToFile();            // $$$
        return ingredient;
    }
    @Override
    public Ingredient getTheIngredient(Integer id) {
        try {
            if (ingredientMap.containsKey(id)) {
                return ingredientMap.get(id);
            } else
                throw new DataNotFoundException();
        }catch (DataNotFoundException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
// ********** Заменённый код **********************
//        if (ingredientMap.containsKey(id)) {
//            return ingredientMap.get(id);
//        } else
//            throw new RuntimeException("Pецепта с таким id нет.");
//    }
    @Override
    public boolean deleteTheIngredient(Integer id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            return true;
        } else
            return false;
    }
    @Override
    public Ingredient editTheIngredient(Integer id, Ingredient ingredient) {
        try {
            if (ingredientMap.containsKey(id)) {
                return ingredientMap.put(id, ingredient);
            } else
                throw new DataNotFoundException();
        } catch (DataNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    // ********** Заменённый код **********************
//        if (ingredientMap.containsKey(id)) {
//             ingredientMap.put(id,ingredient);
//            saveToFile();
//            return ingredient;
//        } else
//            throw new RuntimeException ("Ингредиент не найден.");
//    }
    @Override
    public Collection<Ingredient> getAllIngredient() {
        return ingredientMap.values();
    }
    //---------------------------- $$$ -------------------------------------------------
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();    // throw new RuntimeException(e);   *****
        }

    }
    private void readFromFile() {
        try {
            String json = fileService.readFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();    // throw new RuntimeException(e);  *******************
        }

    }
}
