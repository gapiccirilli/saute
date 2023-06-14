package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.IngredientDto;

public interface IngredientService {
    
    IngredientDto createIngredient(IngredientDto ingredient);
    IngredientDto addIngredientToRecipe(long ingredientId, long recipeId);
    IngredientDto addIngredientToShoppingList(long ingredientId, long shoppingListId);
    IngredientDto getIngredientById(long ingredientId);
    IngredientDto getIngredientByName(String ingredientName);
    List<IngredientDto> getAllIngredients();
    List<IngredientDto> getAllIngredientsByRecipe(long recipeId);
    List<IngredientDto> getAllIngredientsByShoppingList(long shoppingListId);
    IngredientDto updateIngredient(IngredientDto newIngredient, long oldIngredientId);
    String deleteIngredient(long ingredientId);
}
