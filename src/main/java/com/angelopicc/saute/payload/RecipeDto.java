package com.angelopicc.saute.payload;


public class RecipeDto {
    
    private long id;
    private String recipeName;
    private String description;
    
    public RecipeDto(long id, String recipeName, String description) {
        this.id = id;
        this.recipeName = recipeName;
        this.description = description;
    }

    public RecipeDto() {
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

    @Override
    public String toString() {
        return "RecipeDto [id=" + id + ", recipeName=" + recipeName + ", description=" + description + "]";
    }
}
