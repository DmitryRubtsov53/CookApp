package com.example.cookapp.controllers;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.service.IngredientService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{num}")    // Получение рецепта по id.
    public Ingredient getTheIngredient(@PathVariable("num") Integer num) {
        return this.ingredientService.getTheIngredient(num);
    }
}
