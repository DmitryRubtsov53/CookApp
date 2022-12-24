package com.example.cookapp.service;

import com.example.cookapp.model.Ingredient;

import java.util.Collection;

public interface IngredientInterface {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getTheIngredient(Integer id);

    boolean deleteTheIngredient(Integer id);

    Ingredient editTheIngredient(Integer id, Ingredient ingredient);

    Collection<Ingredient> getAllIngredient();
}
