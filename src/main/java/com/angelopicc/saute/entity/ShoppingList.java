package com.angelopicc.saute.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String listName;

    @ManyToMany
    @JoinTable(
        name = "list_recipe",
        joinColumns = @JoinColumn(name = "list_id"),
        inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes = new ArrayList<>();

    // this is for additional ingredients
    
    @OneToMany(mappedBy = "shoppingList", cascade = {CascadeType.DETACH, CascadeType.MERGE, 
    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Item> items = new ArrayList<>();

    public ShoppingList() {
    }

    public ShoppingList(long id, String listName, List<Recipe> recipes, List<Item> items) {
        this.id = id;
        this.listName = listName;
        this.recipes = recipes;
        this.items = items;
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

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShoppingList [id=" + id + ", listName=" + listName + ", recipes=" + recipes + ", items=" + items + "]";
    }
}
