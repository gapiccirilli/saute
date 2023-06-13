package com.angelopicc.saute.payload;

import java.util.List;


public class IngredientDto {
    
    private long id;
    private String ingredientName;
    private double amount;
    private String measurementType;
    // this number represents number of recipes this ingredient is in
    private int numberOfRecipes;
    // used to show all recipes that this ingredient is in
    private List<RecipeDto> recipes;

    public IngredientDto() {

    }

    public IngredientDto(long id, String ingredientName, double amount, String measurementType, int numberOfRecipes,
            List<RecipeDto> recipes) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.measurementType = measurementType;
        this.numberOfRecipes = numberOfRecipes;
        this.recipes = recipes;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public int getNumberOfRecipes() {
        return numberOfRecipes;
    }

    public void setNumberOfRecipes(int numberOfRecipes) {
        this.numberOfRecipes = numberOfRecipes;
    }

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDto> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "IngredientDto [id=" + id + ", ingredientName=" + ingredientName + ", amount=" + amount
                + ", measurementType=" + measurementType + ", numberOfRecipes=" + numberOfRecipes + ", recipes="
                + recipes + "]";
    }
}
