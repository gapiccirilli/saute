package com.angelopicc.saute.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.RecipeBook;
import com.angelopicc.saute.exception.DuplicateNameException;
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
        // 1. check for duplicate recipe book names with helper method
        if (hasNameDuplicate(recipeBook)) {
            throw new DuplicateNameException("There is already a recipe book with the name \"" + recipeBook.getRecipeBookName() + "\"");
        }
        // 2. map to entity
        RecipeBook recipeBookEntity = mapToEntity(recipeBook);
        // 3. save recipebook
        RecipeBook savedEntity = recipeBookRepository.save(recipeBookEntity);
        // 4. map saved entity back to dto and return
        return mapToDto(savedEntity);
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
    
    private boolean hasNameDuplicate(RecipeBookDto recipeBook) {
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findByRecipeBookName(recipeBook.getRecipeBookName());

        if (optRecipeBook.isPresent()) {
            return true;
        }
        return false;
    }

    private RecipeBook mapToEntity(RecipeBookDto dto) {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.setId(dto.getId());
        recipeBook.setRecipeBookName(dto.getRecipeBookName());
        return recipeBook;
    }

    private RecipeBookDto mapToDto(RecipeBook entity) {
        RecipeBookDto dto = new RecipeBookDto(entity.getId(), entity.getRecipeBookName());
        return dto;
    }
}
