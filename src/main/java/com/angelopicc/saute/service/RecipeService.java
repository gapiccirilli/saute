package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.RecipeDto;

public interface RecipeService {
    
    RecipeDto createRecipe(RecipeDto recipe);
    RecipeDto getRecipeById(long recipeId);
    RecipeDto getRecipeByName(String recipeName, long recipeBookId);
    List<RecipeDto> getAllRecipes();
    RecipeDto updateRecipe(RecipeDto newRecipe, long oldRecipeId);
    String deleteRecipe(long recipeId);
}
