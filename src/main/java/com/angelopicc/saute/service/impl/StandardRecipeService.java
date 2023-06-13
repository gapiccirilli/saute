package com.angelopicc.saute.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.payload.RecipeDto;
import com.angelopicc.saute.service.RecipeService;

@Service
public class StandardRecipeService implements RecipeService {

    @Override
    public RecipeDto createRecipe(RecipeDto recipe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRecipe'");
    }

    @Override
    public RecipeDto getRecipeById(long recipeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecipeById'");
    }

    @Override
    public RecipeDto getRecipeByName(String recipeName, long recipeBookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecipeByName'");
    }

    @Override
    public List<RecipeDto> getAllRecipes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRecipes'");
    }

    @Override
    public RecipeDto updateRecipe(RecipeDto newRecipe, long oldRecipeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipe'");
    }

    @Override
    public String deleteRecipe(long recipeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecipe'");
    }
    
}
