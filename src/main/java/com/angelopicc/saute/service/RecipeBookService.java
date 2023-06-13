package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.RecipeBookDto;

public interface RecipeBookService {
    
    RecipeBookDto createRecipeBook(RecipeBookDto recipeBook);
    RecipeBookDto getRecipeBookById(long recipeBookId);
    RecipeBookDto getRecipeBookByName(String recipeBookName);
    List<RecipeBookDto> getAllRecipeBooks();
    RecipeBookDto updateRecipeBook(RecipeBookDto newRecipeBook, long oldRecipeBookId);
    String deleteRecipeBook(long recipeBookId);
}
