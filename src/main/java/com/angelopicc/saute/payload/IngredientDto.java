package com.angelopicc.saute.payload;


public class IngredientDto {
    
    private long id;
    private String ingredientName;
    // this number represents number of recipes this ingredient is in
    private int numberOfRecipes;

    private final String type = "Ingredient";

    public IngredientDto() {

    }

    public IngredientDto(long id, String ingredientName, int numberOfRecipes) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.numberOfRecipes = numberOfRecipes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getNumberOfRecipes() {
        return numberOfRecipes;
    }

    public void setNumberOfRecipes(int numberOfRecipes) {
        this.numberOfRecipes = numberOfRecipes;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "IngredientDto [id=" + id + ", ingredientName=" + ingredientName + ", numberOfRecipes=" + numberOfRecipes + "]";
    }
}
