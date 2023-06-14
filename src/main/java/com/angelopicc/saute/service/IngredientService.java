package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.IngredientDto;

public interface IngredientService {
    
    IngredientDto createIngredient(IngredientDto ingredient);
    IngredientDto createIngredientForRecipe(IngredientDto ingredient, long recipeId);
    IngredientDto getIngredientById(long ingredientId);
    IngredientDto getIngredientByName(String ingredientName);
    List<IngredientDto> getAllIngredients(long recipeId);
    IngredientDto updateIngredient(IngredientDto newIngredient, long oldIngredientId);
    String deleteIngredient(long ingredientId);
}
