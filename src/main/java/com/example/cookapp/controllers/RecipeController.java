package com.example.cookapp.controllers;

import com.example.cookapp.model.Recipe;
import com.example.cookapp.service.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Кулинарная книга", description = "CRUD-операции с рецептами приготовления блюд.")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/")
    public Recipe creatRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }
    @GetMapping("/")
    public Collection<Recipe> getAll(){
        return this.recipeService.getAllRecipe();
    }
    @GetMapping("/{id}")    // Получение рецепта по id.
    public Recipe getRecipe(@PathVariable("id") Integer id){
        return this.recipeService.getTheRecipe(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Integer id) {
        if (recipeService.deleteTheRecipe(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe) {
        recipe = recipeService.editTheRecipe(id,recipe);
        if (recipe != null) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
