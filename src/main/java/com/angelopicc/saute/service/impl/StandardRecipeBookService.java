package com.angelopicc.saute.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.payload.RecipeBookDto;
import com.angelopicc.saute.repository.RecipeBookRepository;
import com.angelopicc.saute.service.RecipeBookService;

@Service
public class StandardRecipeBookService implements RecipeBookService {

    private RecipeBookRepository recipeBookRepository;

    public StandardRecipeBookService(RecipeBookRepository recipeBookRepository) {
        this.recipeBookRepository = recipeBookRepository;
    }

    @Override
    public RecipeBookDto createRecipeBook(RecipeBookDto recipeBook) {
        return null;
    }

    @Override
    public RecipeBookDto getRecipeBookById(long recipeBookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecipeBookById'");
    }

    @Override
    public RecipeBookDto getRecipeBookByName(String recipeBookName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecipeBookByName'");
    }

    @Override
    public List<RecipeBookDto> getAllRecipeBooks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRecipeBooks'");
    }

    @Override
    public RecipeBookDto updateRecipeBook(RecipeBookDto newRecipeBook, long oldRecipeBookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipeBook'");
    }

    @Override
    public String deleteRecipeBook(long recipeBookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecipeBook'");
    }
    
}
