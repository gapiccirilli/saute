package com.angelopicc.saute.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.RecipeBook;
import com.angelopicc.saute.exception.DuplicateNameException;
import com.angelopicc.saute.payload.RecipeBookDto;
import com.angelopicc.saute.repository.RecipeBookRepository;
import com.angelopicc.saute.service.RecipeBookService;
import com.angelopicc.saute.utility.comparator.RecipeBookComparator;

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
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findById(recipeBookId);
        checkEntityExists(optRecipeBook, "Recipe book with id: " + recipeBookId + ", cannot be found");

        return mapToDto(optRecipeBook.get());
    }

    @Override
    public RecipeBookDto getRecipeBookByName(String recipeBookName) {
        // 1. check if entity exists
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findByRecipeBookName(recipeBookName);
        checkEntityExists(optRecipeBook, "Recipe book with name: \"" + recipeBookName + "\", cannot be found");
        // 2. map to dto and return
        return mapToDto(optRecipeBook.get());
    }

    @Override
    public List<RecipeBookDto> getAllRecipeBooks() {
        List<RecipeBook> recipeBooks = recipeBookRepository.findAll();

        if (recipeBooks.isEmpty() || recipeBooks == null) {
            throw new EntityNotFoundException("No recipe books");
        }
        
        return mapListToDto(recipeBooks);
    }

    @Override
    public RecipeBookDto updateRecipeBook(RecipeBookDto newRecipeBook, long oldRecipeBookId) {
        // 1. check that entity exists
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findById(oldRecipeBookId);
        checkEntityExists(optRecipeBook, "Recipe book with id: " + oldRecipeBookId + ", cannot be found");
        // 2. make changes to recipe book
        RecipeBook recipeBook = optRecipeBook.get();
        recipeBook.setId(newRecipeBook.getId());
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
        checkEntityExists(optRecipeBook, "Recipe book with id: " + recipeBookId + ", cannot be found");
        // delete entity
        recipeBookRepository.deleteById(recipeBookId);
        // return Success message
        return "Successfully Deleted!";
    }
    
    // ------------------------------------------------------------------------------------------------------------|

    // ------------------------------------------------------------------------------------------------------------|

    private boolean hasNameDuplicate(RecipeBookDto recipeBook) {
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findByRecipeBookName(recipeBook.getRecipeBookName());

        if (optRecipeBook.isPresent()) {
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
            RecipeBookDto dto = new RecipeBookDto(book.getId(), book.getRecipeBookName());
            return dto;
        })
        .collect(Collectors.toList());
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
