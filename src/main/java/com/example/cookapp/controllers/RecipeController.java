package com.example.cookapp.controllers;

import com.example.cookapp.model.Recipe;
import com.example.cookapp.service.RecipeService;
import com.example.cookapp.service.impl.RecipeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Кулинарная книга", description = "CRUD-операции с рецептами приготовления блюд.")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

//---------------------------------------------------------------------
    @PostMapping("/")
    @Operation( summary = "Добавление рецепта в Кулинарную книгу."    )
    @ApiResponses ( {
            @ApiResponse ( responseCode = "200",
                    description = "Рецепт добавлен успешно.",
                    content = {  @Content (mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class)
                    )}
            ),
            @ApiResponse( responseCode = "500",
                    description = "Ошибка сервера. Повторите запрос."  )
    } )
    public Recipe creatRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }
//-------------------------------------------------------------------------
    @GetMapping("/")
    @Operation( summary = "Просмотр всех рецептов Кулинарной книги."    )
    @ApiResponses ( {
            @ApiResponse ( responseCode = "200",
                    description = "Запрос выполнен успешно.",
                    content = {  @Content (mediaType = "application/json") }
            ),
            @ApiResponse( responseCode = "500",
                    description = "Ошибка сервера. Повторите запрос."  )
    } )
    public Collection<Recipe> getAll(){
        return this.recipeService.getAllRecipe();
    }
//-------------------------------------------------------------------------------
    @GetMapping("/{id}")    // Получение рецепта по id.
    @Operation( summary = "Просмотр рецепта Кулинарной книги с указанным id."    )
    @ApiResponses ( {
            @ApiResponse ( responseCode = "200",
                    description = "Запрос выполнен успешно.",
                    content = {  @Content (mediaType = "application/json") }
            ),
            @ApiResponse( responseCode = "404",
                    description = "Рецепт c введённым id не найден."  ),
            @ApiResponse( responseCode = "500",
                    description = "Ошибка сервера. Повторите запрос."  )
    } )
    public Recipe getRecipe(@PathVariable("id") Integer id){
        return this.recipeService.getTheRecipe(id);
    }
//-------------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation( summary = "Удаление рецепта Кулинарной книги с указанным id."    )
    @ApiResponses ( {
            @ApiResponse ( responseCode = "200",
                    description = "Удаление выполнено успешно.",
                    content = { @Content (mediaType = "application/json") }
            ),
            @ApiResponse( responseCode = "404",
                    description = "Рецепт c введённым id не найден."  ),
            @ApiResponse( responseCode = "500",
                    description = "Ошибка сервера. Повторите запрос."  )
    } )
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Integer id) {
        if (recipeService.deleteTheRecipe(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
//-------------------------------------------------------------------------------
    @PutMapping("/{id}")
    @Operation( summary = "Редактирование рецепта Кулинарной книги с указанным id."    )
    @ApiResponses ( {
            @ApiResponse ( responseCode = "200",
                    description = "Рецепт добавлен успешно.",
                    content = {  @Content (mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class)
                    )}
            ),
            @ApiResponse( responseCode = "500",
                    description = "Ошибка сервера. Повторите запрос."  )
    } )
    public ResponseEntity<Recipe> editRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe) {
        recipe = recipeService.editTheRecipe(id,recipe);
        if (recipe != null) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
    @GetMapping("/list")
    @Operation( summary = "Скачать все рецепты из приложения в одном файле."    )
    public ResponseEntity<Object> getListOfAllRecipes() {
        try {
            Path path = recipeService.createListOfAllRecipes();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ListOfAllRecipes.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
