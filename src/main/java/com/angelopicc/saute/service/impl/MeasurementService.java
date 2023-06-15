package com.angelopicc.saute.service.impl;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Recipe;
import com.angelopicc.saute.entity.ShoppingList;
import com.angelopicc.saute.payload.IngredientDto;

@Service
public class MeasurementService {
    
    public void updateMeasurementForRecipe(IngredientDto ingredient, long oldIngredientId, Recipe recipe) {
        // 1. getMeasurements() from recipe
        // 2. find ingredient by oldIngredientId
        // 3. use old ingredient entity and find measurement that matches name
        // 4. update ingredientName in measurement from ingredientDto
        // 5. persist measurement entity
    }

    public void updateMeasurementForShoppingList(IngredientDto ingredient, long oldIngredientId, ShoppingList shoppingList) {
        // 1. getMeasurements() from shoppingList
        // 2. find ingredient by oldIngredientId
        // 3. use old ingredient entity and find measurement that matches name
        // 4. update ingredientName in measurement from ingredientDto
        // 5. persist measurement entity
    }
}
