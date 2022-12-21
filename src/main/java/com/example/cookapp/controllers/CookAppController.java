package com.example.cookapp.controllers;

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
    @GetMapping("/all")
    public Collection<Recipe> getAll(){
        return this.cookAppService.getAllRecipe();

    }
    @GetMapping("/{id}")    // Получение рецепта по id.
    public Recipe getRecipe(@PathVariable("id") Integer id){
        return this.cookAppService.getTheRecipe(id);
    }

}
