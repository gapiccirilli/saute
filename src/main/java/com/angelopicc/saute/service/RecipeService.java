package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.RecipeDto;

public interface RecipeService {
    
    RecipeDto createRecipe(RecipeDto recipe, long recipeBookId);
    RecipeDto addRecipeToShoppingList(long recipeId, long shoppingListId);
    RecipeDto getRecipeById(long recipeId);
    List<RecipeDto> getRecipeByName(String recipeName, long recipeBookId);
    List<RecipeDto> getRecipeByName(String recipeName);
    List<RecipeDto> getRecipesByShoppingList(long shoppingListId);
    List<RecipeDto> getAllRecipes(long recipeBookId);
    RecipeDto updateRecipe(RecipeDto newRecipe, long oldRecipeId);
    String removeRecipeFromShoppingList(long recipeId, long shoppingListId);
    String deleteRecipe(long recipeId);
}
