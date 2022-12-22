package com.example.cookapp.controllers;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.model.Recipe;
import com.example.cookapp.service.CookAppService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cookapp")
public class CookAppController {
    private final CookAppService cookAppService;

    public CookAppController(CookAppService cookAppService) {
        this.cookAppService = cookAppService;
    }
    @PostMapping("/creat")
    public Recipe creatRecipe(@RequestBody Recipe recipe) {
        return this.cookAppService.addRecipe(recipe);
    }
    @GetMapping("/allrec")
    public Collection<Recipe> getAll(){
        return this.cookAppService.getAllRecipe();
    }
    @GetMapping("rec/{id}")    // Получение рецепта по id.
    public Recipe getRecipe(@PathVariable("id") Integer id){
        return this.cookAppService.getTheRecipe(id);
    }
    // Ingredients...........................................................................
//    @PostMapping("/add")
//    public Ingredient creatIngredient(@RequestBody Ingredient ingredient) { // переносим в IngredientService
//        return this.cookAppService.addIngredient(ingredient);
//    }
//    @GetMapping("ing/{num}")    // Получение рецепта по id.
//    public Ingredient getTheIngredient(@PathVariable("num") Integer num) { // переносим в IngredientService
//        return this.cookAppService.getTheIngredient(num);
//    }
}
