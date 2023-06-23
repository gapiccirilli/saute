package com.angelopicc.saute.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angelopicc.saute.payload.RecipeDto;
import com.angelopicc.saute.service.RecipeService;

@RestController
@RequestMapping("/api")
public class RecipeRestController {
    
    private RecipeService recipeService;

    public RecipeRestController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipe-books/{recipeBookId}/recipes")
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipe, @PathVariable long recipeBookId) {

        return new ResponseEntity<>(recipeService.createRecipe(recipe, recipeBookId), HttpStatus.CREATED);
    }

    @GetMapping("/recipes/{recipeId}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable long recipeId) {

        return new ResponseEntity<>(recipeService.getRecipeById(recipeId), HttpStatus.OK);
    }

    @GetMapping("/recipe-books/{recipeBookId}/recipes/search")
    public ResponseEntity<RecipeDto> getRecipeByName(@RequestParam String recipeName, @PathVariable long recipeBookId) {
        return new ResponseEntity<RecipeDto>(recipeService.getRecipeByName(recipeName, recipeBookId), HttpStatus.OK);
    }

    @GetMapping("/recipe-books/{recipeBookId}/recipes")
    public ResponseEntity<List<RecipeDto>> getAllRecipes(@PathVariable long recipeBookId) {
        return new ResponseEntity<>(recipeService.getAllRecipes(recipeBookId), HttpStatus.OK);
    }

    @PutMapping("/recipes/{oldRecipeId}")
    public ResponseEntity<RecipeDto> updateRecipe(@RequestBody RecipeDto newRecipe, @PathVariable long oldRecipeId) {
        return new ResponseEntity<>(recipeService.updateRecipe(newRecipe, oldRecipeId), HttpStatus.OK);
    }

    @DeleteMapping("/recipes/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable long recipeId) {
        return new ResponseEntity<>(recipeService.deleteRecipe(recipeId), HttpStatus.OK);
    }
}
