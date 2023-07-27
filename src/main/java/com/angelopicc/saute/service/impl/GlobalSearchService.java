package com.angelopicc.saute.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.service.IngredientService;
import com.angelopicc.saute.service.RecipeBookService;
import com.angelopicc.saute.service.RecipeService;
import com.angelopicc.saute.service.SearchService;
import com.angelopicc.saute.service.ShoppingListService;

@Service
public class GlobalSearchService implements SearchService<Object>{

    private IngredientService ingredientService;
    private RecipeBookService recipeBookService;
    private RecipeService recipeService;
    private ShoppingListService shoppingListService;

    public GlobalSearchService(IngredientService ingredientService, RecipeBookService recipeBookService,
            RecipeService recipeService, ShoppingListService shoppingListService) {
        this.ingredientService = ingredientService;
        this.recipeBookService = recipeBookService;
        this.recipeService = recipeService;
        this.shoppingListService = shoppingListService;
    }

    @Override
    public List<Object> search(String query) {
        
        return null;
    }
}
