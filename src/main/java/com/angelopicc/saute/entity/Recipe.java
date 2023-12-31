package com.angelopicc.saute.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String recipeName;

    private String description;

    private String image;

    @ManyToMany(mappedBy = "recipes", 
    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ShoppingList> shoppingLists = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "recipe_book_id")
    private RecipeBook recipeBook;

    public Recipe() {
    }

    public Recipe(long id, String recipeName, String description, String image, List<ShoppingList> shoppingLists,
            List<Item> items, RecipeBook recipeBook) {
        this.id = id;
        this.recipeName = recipeName;
        this.description = description;
        this.image = image;
        this.shoppingLists = shoppingLists;
        this.items = items;
        this.recipeBook = recipeBook;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RecipeBook getRecipeBook() {
        return recipeBook;
    }

    public void setRecipeBook(RecipeBook recipeBook) {
        this.recipeBook = recipeBook;
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public void addShoppingList(ShoppingList shoppingList) {
        this.shoppingLists.add(shoppingList);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", recipeName=" + recipeName + ", description=" + description + ", image=" + image + "]";
    }
}
