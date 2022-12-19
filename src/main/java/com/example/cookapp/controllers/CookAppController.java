package com.example.cookapp.controllers;

import com.example.cookapp.model.Recipe;
import com.example.cookapp.service.CookAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/cookapp")
public class CookAppController {
    private final CookAppService cookAppService;

    public CookAppController(CookAppService cookAppService) {
        this.cookAppService = cookAppService;
    }

    @GetMapping
    public Recipe creatRecipe(@RequestBody Recipe recipe) {
        return this.cookAppService.addRecipe(recipe);
    }

    @GetMapping
    public Collection<Recipe> getAll(){
        return this.cookAppService.getAllRecipe();
    }

}
