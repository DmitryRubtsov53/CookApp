package com.example.cookapp.controllers;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.model.Recipe;
import com.example.cookapp.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngregientController {

    private final IngredientService ingredientService;

    public IngregientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/add")
    public Ingredient creatIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }
    @GetMapping("/alling")
    public Collection<Ingredient> getAllIngredient(){
        return this.ingredientService.getAllIngredient();
    }
    @GetMapping("/{num}")    // Получение рецепта по id.
    public Ingredient getTheIngredient(@PathVariable("num") Integer num) {
        return this.ingredientService.getTheIngredient(num);
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Ingredient> deleteRecipe(@PathVariable("id") Integer id) {
        if (ingredientService.deleteTheIngredient(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Ingredient> editRecipe(@PathVariable("id") Integer id, @RequestBody Ingredient ingredient) {
        ingredient = ingredientService.editTheIngredient(id,ingredient);
        if (ingredient != null) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
