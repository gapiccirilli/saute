package com.angelopicc.saute.payload;


public class IngredientDto {
    
    private long id;
    private String ingredientName;
    // this number represents number of recipes this ingredient is in
    private int numberOfRecipes;
    private int amount;
    private String measurmentType;

    public IngredientDto() {

    }

    public IngredientDto(long id, String ingredientName, int numberOfRecipes, int amount, String measurmentType) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.numberOfRecipes = numberOfRecipes;
        this.amount = amount;
        this.measurmentType = measurmentType;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMeasurmentType() {
        return measurmentType;
    }

    public void setMeasurmentType(String measurmentType) {
        this.measurmentType = measurmentType;
    }

    @Override
    public String toString() {
        return "IngredientDto [id=" + id + ", ingredientName=" + ingredientName + ", numberOfRecipes=" + numberOfRecipes
                + ", amount=" + amount + ", measurmentType=" + measurmentType + "]";
    }
}
