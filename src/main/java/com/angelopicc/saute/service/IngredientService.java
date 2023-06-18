package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.IngredientDto;

public interface IngredientService {
    
    IngredientDto createIngredient(IngredientDto ingredient);
    IngredientDto getIngredientById(long ingredientId);
    List <IngredientDto> getIngredientByName(String ingredientName);
    List<IngredientDto> getAllIngredients();
    IngredientDto updateIngredient(IngredientDto newIngredient, long oldIngredientId);
    String deleteIngredient(long ingredientId);
}
