package com.example.cookapp.controllers;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.service.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Список ингредиентов", description = "CRUD-операции с ингредиентами кулинарных блюд.")
public class IngregientController {

    private final IngredientService ingredientService;

    public IngregientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/")
    public Ingredient creatIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }
    @GetMapping("/")
    public Collection<Ingredient> getAllIngredient(){
        return this.ingredientService.getAllIngredient();
    }
    @GetMapping("/{id}")    // Получение рецепта по id.
    public Ingredient getTheIngredient(@PathVariable("id") Integer id) {
        return this.ingredientService.getTheIngredient(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteRecipe(@PathVariable("id") Integer id) {
        if (ingredientService.deleteTheIngredient(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editRecipe(@PathVariable("id") Integer id, @RequestBody Ingredient ingredient) {
        ingredient = ingredientService.editTheIngredient(id,ingredient);
        if (ingredient != null) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
