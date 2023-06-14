package com.angelopicc.saute.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ingredientName;

    private double amount;

    private String measurementType;

    private String image;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes = new ArrayList<>();;

    @ManyToMany(mappedBy = "ingredients")
    private List<ShoppingList> shoppingLists = new ArrayList<>();

    public Ingredient() {
    }

    public Ingredient(long id, String ingredientName, double amount, String measurementType,
            String image) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.measurementType = measurementType;
        this.image = image;
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
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public void addShoppingList(ShoppingList shoppingList) {
        this.shoppingLists.add(shoppingList);
    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", ingredientName=" + ingredientName + ", amount=" + amount
                + ", measurementType=" + measurementType + ", image=" + image + ", recipes=" + recipes
                + ", shoppingLists=" + shoppingLists + "]";
    }
}
