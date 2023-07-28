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
@Table(name = "ingredient")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ingredientName;

    private String image;

    @OneToMany(mappedBy = "ingredient", cascade = {CascadeType.ALL})
    private List<Item> items = new ArrayList<>();

    public Ingredient() {
    }

    public Ingredient(long id, String ingredientName, String image, List<Item> items) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.image = image;
        this.items = items;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", ingredientName=" + ingredientName + ", image=" + image + ", items=" + items
                + "]";
    }
}
