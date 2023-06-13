package com.angelopicc.saute.payload;

public class RecipeBookDto {
    
    private long id;
    private String recipeBookName;
    
    public RecipeBookDto() {
    }

    public RecipeBookDto(long id, String recipeBookName) {
        this.id = id;
        this.recipeBookName = recipeBookName;
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

    @Override
    public String toString() {
        return "RecipeBookDto [id=" + id + ", recipeBookName=" + recipeBookName + "]";
    }
}
