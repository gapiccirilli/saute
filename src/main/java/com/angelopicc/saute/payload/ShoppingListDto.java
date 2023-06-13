package com.angelopicc.saute.payload;

import java.util.List;


public class ShoppingListDto {
    
    private long id;
    private String listName;
    private List<RecipeDto> recipes;
    private List<IngredientDto> ingredients;
    
    public ShoppingListDto() {
    }

    public ShoppingListDto(long id, String listName, List<RecipeDto> recipes, List<IngredientDto> ingredients) {
        this.id = id;
        this.listName = listName;
        this.recipes = recipes;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDto> recipes) {
        this.recipes = recipes;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "ShoppingListDto [id=" + id + ", listName=" + listName + ", recipes=" + recipes + ", ingredients="
                + ingredients + "]";
    }
}
