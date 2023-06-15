package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.IngredientDto;

public interface IngredientService {
    
    IngredientDto createIngredient(IngredientDto ingredient);
    IngredientDto addIngredientToRecipe(IngredientDto ingredientId, long recipeId);
    List<IngredientDto> addIngredientsToRecipe(List<IngredientDto> ingredients, long recipeId);
    IngredientDto addIngredientToShoppingList(long ingredient, long shoppingListId);
    IngredientDto getIngredientById(long ingredientId);
    IngredientDto getIngredientByName(String ingredientName);
    List<IngredientDto> getAllIngredients();
    List<IngredientDto> getAllIngredientsByRecipe(long recipeId);
    List<IngredientDto> getAllIngredientsByShoppingList(long shoppingListId);
    IngredientDto updateIngredient(IngredientDto newIngredient, long oldIngredientId);
    IngredientDto updateIngredientByRecipe(IngredientDto newIngredient, long oldIngredientId, long recipeId);
    IngredientDto updateIngredientByShoppingList(IngredientDto newIngredient, long oldIngredientId, long shoppingListId);
    String deleteIngredient(long ingredientId);
}
