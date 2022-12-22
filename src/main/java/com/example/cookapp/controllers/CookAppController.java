package com.example.cookapp.controllers;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.model.Recipe;
import com.example.cookapp.service.CookAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cookapp")
public class CookAppController {
    private final CookAppService cookAppService;

    public CookAppController(CookAppService cookAppService) { this.cookAppService = cookAppService;
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

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Integer id) {
        if (cookAppService.deleteTheRecipe(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe) {
        recipe = cookAppService.editTheRecipe(id,recipe);
        if (recipe != null) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
