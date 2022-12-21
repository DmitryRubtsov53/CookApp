package com.example.cookapp.model;

import java.util.ArrayList;
import java.util.Objects;

public class Recipe {
    private final String recipeName;
    private final int cookingTime;
    private ArrayList<String> cookingStep;
    private ArrayList<Ingredient> ingredients;

    public Recipe(String recipeName, int cookingTime, ArrayList<Ingredient> ingredients, ArrayList<String> cookingStep) {
        this.recipeName = recipeName;
        this.cookingTime = cookingTime;
        this.cookingStep = cookingStep;
        this.ingredients = ingredients;
    }

    public String getRecipeName() { return recipeName;
    }

    public int getCookingTime() {  return cookingTime;
    }

    public ArrayList<String> getCookingStep() { return cookingStep;
    }

    public ArrayList<Ingredient> getIngredient() { return ingredients;
    }

    public void setCookingStep(ArrayList<String> cookingStep) {
        this.cookingStep = cookingStep;
    }

    public void setIngredient(ArrayList<Ingredient> ingredient) {
        this.ingredients = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return cookingTime == recipe.cookingTime && Objects.equals(recipeName, recipe.recipeName)
                && Objects.equals(cookingStep, recipe.cookingStep)
                && Objects.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeName, cookingTime, cookingStep, ingredients);
    }

    @Override
    public String toString() {
        return "Recipe: " + recipeName + ", cookingTime=" + cookingTime + ", ingredient { " + ingredients +
                ", cookingStep { " + cookingStep +" }";

    }
}
