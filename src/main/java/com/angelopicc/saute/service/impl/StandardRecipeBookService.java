package com.angelopicc.saute.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Ingredient;
import com.angelopicc.saute.entity.RecipeBook;
import com.angelopicc.saute.exception.DeleteFailedException;
import com.angelopicc.saute.exception.DuplicateNameException;
import com.angelopicc.saute.exception.NoBooksFoundException;
import com.angelopicc.saute.exception.NoIngredientsFoundException;
import com.angelopicc.saute.exception.NoRecipesFoundException;
import com.angelopicc.saute.payload.RecipeBookDto;
import com.angelopicc.saute.repository.RecipeBookRepository;
import com.angelopicc.saute.service.RecipeBookService;
import static com.angelopicc.saute.utility.message.StatusMessage.DELETE_SUCCESSFUL;
import static com.angelopicc.saute.utility.message.StatusMessage.NO_BOOKS_FOUND;

import jakarta.persistence.EntityNotFoundException;

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
            throw new DuplicateNameException("There is already a recipe book with the name '" + recipeBook.getRecipeBookName() + "'");
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
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findById(recipeBookId);
        checkEntityExists(optRecipeBook, "Recipe book with id: '" + recipeBookId + "', cannot be found");

        return mapToDto(optRecipeBook.get());
    }

    @Override
    public List<RecipeBookDto> getRecipeBooksByName(String recipeBookName) {
       List<RecipeBook> searchedRecipeBooks = recipeBookRepository.findByRecipeBookNameStartingWith(recipeBookName);

        // if (searchedRecipeBooks.isEmpty()) {
        //     throw new NoBooksFoundException(NO_BOOKS_FOUND);
        // }
        return mapListToDto(searchedRecipeBooks);
    }

    @Override
    public List<RecipeBookDto> getAllRecipeBooks() {
        List<RecipeBook> recipeBooks = recipeBookRepository.findAll();

        if (recipeBooks.isEmpty() || recipeBooks == null) {
            throw new NoRecipesFoundException("No recipe books");
        }
        
        return mapListToDto(recipeBooks);
    }

    @Override
    public RecipeBookDto updateRecipeBook(RecipeBookDto newRecipeBook, long oldRecipeBookId) {
        // 1. check that entity exists
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findById(oldRecipeBookId);
        checkEntityExists(optRecipeBook, "Recipe book with id: '" + oldRecipeBookId + "', cannot be found");
        // 2. make changes to recipe book
        RecipeBook recipeBook = optRecipeBook.get();

        if (hasNameDuplicate(newRecipeBook)) {
            throw new DuplicateNameException("Recipe book '" + newRecipeBook.getRecipeBookName() + "' already exists");
        }
        recipeBook.setRecipeBookName(newRecipeBook.getRecipeBookName());
        // 3. store recipe book
        RecipeBook savedRecipeBook = recipeBookRepository.save(recipeBook);
        // 4. map and return dto
        return mapToDto(savedRecipeBook);
    }

    @Override
    public String deleteRecipeBook(long recipeBookId) {
        // check that entity exists
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findById(recipeBookId);
        checkEntityExists(optRecipeBook, "Recipe book with id: '" + recipeBookId + "', cannot be found");
        // delete entity
        recipeBookRepository.deleteById(recipeBookId);
        Optional<RecipeBook> deletedEntity = recipeBookRepository.findById(recipeBookId);
        if (deletedEntity.isPresent()) {
            throw new DeleteFailedException();
        }
        // return Success message
        return DELETE_SUCCESSFUL;
    }
    
    // ------------------------------------------------------------------------------------------------------------|

    // ------------------------------------------------------------------------------------------------------------|

    private boolean hasNameDuplicate(RecipeBookDto recipeBook) {
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findByRecipeBookName(recipeBook.getRecipeBookName());

        if (optRecipeBook.isPresent() && optRecipeBook.get().getId() != recipeBook.getId()) {
            return true;
        }
        return false;
    }

    private void checkEntityExists(Optional<RecipeBook> entity, String failMessage) {
        if (!entity.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private List<RecipeBookDto> mapListToDto(List<RecipeBook> entityList) {
        return entityList.stream()
        .map(book -> {
            RecipeBookDto dto = mapToDto(book);
            return dto;
        })
        .collect(Collectors.toList());
    }

    private RecipeBook mapToEntity(RecipeBookDto dto) {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.setRecipeBookName(dto.getRecipeBookName());
        return recipeBook;
    }

    private RecipeBookDto mapToDto(RecipeBook entity) {
        RecipeBookDto dto = new RecipeBookDto(entity.getId(), entity.getRecipeBookName());
        return dto;
    }
}
