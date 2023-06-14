package com.angelopicc.saute.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe_book")
public class RecipeBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String recipeBookName;

    @OneToMany(mappedBy = "recipeBook", cascade = CascadeType.ALL)
    private List<Recipe> recipes = new ArrayList<>();

    public RecipeBook() {
    }

    public RecipeBook(long id, String recipeBookName, List<Recipe> recipes) {
        this.id = id;
        this.recipeBookName = recipeBookName;
        this.recipes = recipes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipeBookName() {
        return recipeBookName;
    }

    public void setRecipeBookName(String recipeBookName) {
        this.recipeBookName = recipeBookName;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "RecipeBook [id=" + id + ", recipeBookName=" + recipeBookName + ", recipes=" + recipes + "]";
    }
}
