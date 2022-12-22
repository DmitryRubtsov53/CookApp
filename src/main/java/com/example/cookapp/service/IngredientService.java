package com.example.cookapp.service;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService {

    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();

    public Ingredient addIngredient(Ingredient ingredient) {  // переносим в IngredientService
        return ingredientMap.put(ingredientMap.size(), ingredient);
    }

    public Ingredient getTheIngredient(Integer id) { // переносим в IngredientService
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.get(id);
        } else
            throw new RuntimeException("Pецепта с таким id нет.");
    }
    public boolean deleteTheIngredient(Integer id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            return true;
        } else
            return false;
    }
    public Ingredient editTheIngredient (Integer id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.put(id,ingredient);
        } else
            return null;
    }
    public Collection<Ingredient> getAllIngredient() {
        return ingredientMap.values();
    }

}
