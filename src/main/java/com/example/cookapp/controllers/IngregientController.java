package com.example.cookapp.controllers;

import com.example.cookapp.model.Ingredient;
import com.example.cookapp.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
//------------------------------------------------------------------------
    @PostMapping("/")
    @Operation( summary = "Добавление ингредиента в Кулинарную книгу."    )
    @ApiResponses(
            @ApiResponse( responseCode = "200",
                    description = "Ингредиент добавлен успешно.",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            )
    )
    public Ingredient creatIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }
//-------------------------------------------------------------------------
    @GetMapping("/")
    @Operation( summary = "Просмотр всех ингредиентов Кулинарной книги."    )
    @ApiResponses (
            @ApiResponse ( responseCode = "200",
                    description = "Запрос выполнен успешно.",
                    content = {
                            @Content (mediaType = "application/json")
                    }
            )
    )
    public Collection<Ingredient> getAllIngredient(){
        return this.ingredientService.getAllIngredient();
    }
//-------------------------------------------------------------------------
    @GetMapping("/{id}")
    @Operation( summary = "Просмотр ингредиента Кулинарной книги с указанным id."    )
    @ApiResponses (
            @ApiResponse ( responseCode = "200",
                    description = "Запрос выполнен успешно.",
                    content = {
                            @Content (mediaType = "application/json")
                    }
            )
    )
    public Ingredient getTheIngredient(@PathVariable("id") Integer id) {
        return this.ingredientService.getTheIngredient(id);
    }
//-------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation( summary = "Удаление ингредиента Кулинарной книги с указанным id."    )
    @ApiResponses (
            @ApiResponse ( responseCode = "200",
                    description = "Удаление выполнено успешно.",
                    content = {
                            @Content (mediaType = "application/json")
                    }
            )
    )
    public ResponseEntity<Ingredient> deleteRecipe(@PathVariable("id") Integer id) {
        if (ingredientService.deleteTheIngredient(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
//--------------------------------------------------------------------------
    @PutMapping("/{id}")
    @Operation( summary = "Редактирование ингредиента Кулинарной книги с указанным id."    )
    @ApiResponses (
            @ApiResponse ( responseCode = "200",
                    description = "Редактирование выполнено успешно.",
                    content = {
                            @Content (mediaType = "application/json")
                    }
            )
    )
    public ResponseEntity<Ingredient> editRecipe(@PathVariable("id") Integer id, @RequestBody Ingredient ingredient) {
        ingredient = ingredientService.editTheIngredient(id,ingredient);
        if (ingredient != null) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
