package com.angelopicc.saute.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.payload.IngredientDto;
import com.angelopicc.saute.repository.IngredientRepository;
import com.angelopicc.saute.repository.RecipeRepository;
import com.angelopicc.saute.service.IngredientService;

@Service
public class StandardIngredientService implements IngredientService {

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    @Override
    public IngredientDto createIngredient(IngredientDto ingredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createIngredient'");
    }

    @Override
    public IngredientDto addIngredientToRecipe(long ingredientId, long recipeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createIngredientForRecipe'");
    }

    @Override
    public IngredientDto addIngredientToShoppingList(long ingredientId, long shoppingListId) {
        return null;
    }

    @Override
    public IngredientDto getIngredientById(long ingredientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIngredientById'");
    }

    @Override
    public IngredientDto getIngredientByName(String ingredientName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIngredientByName'");
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllIngredients'");
    }

    @Override
    public List<IngredientDto> getAllIngredientsByRecipe(long recipeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllIngredients'");
    }

    @Override
    public List<IngredientDto> getAllIngredientsByShoppingList(long shoppingListId) {
        return null;
    }

    @Override
    public IngredientDto updateIngredient(IngredientDto newIngredient, long oldIngredientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateIngredient'");
    }

    @Override
    public String deleteIngredient(long ingredientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteIngredient'");
    }
    
}
