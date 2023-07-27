package com.angelopicc.saute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.payload.IngredientDto;
import com.angelopicc.saute.payload.RecipeBookDto;
import com.angelopicc.saute.payload.RecipeDto;
import com.angelopicc.saute.payload.ShoppingListDto;
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
        List<Object> searchItems = new ArrayList<>();

        List<IngredientDto> ingredients = ingredientService.getIngredientsByName(query);
        List<RecipeBookDto> recipeBooks = recipeBookService.getRecipeBooksByName(query);
        List<RecipeDto> recipes = recipeService.getRecipesByName(query);
        List<ShoppingListDto> shoppingLists = shoppingListService.getShoppingListsByName(query);

        if (!ingredients.isEmpty()) {
            searchItems.addAll(ingredients);
        }

        if (!recipeBooks.isEmpty()) {
            searchItems.addAll(recipeBooks);
        }

        if (!recipes.isEmpty()) {
            searchItems.addAll(recipes);
        }

        if (!shoppingLists.isEmpty()) {
            searchItems.addAll(shoppingLists);
        }

        return searchItems;
    }
}
