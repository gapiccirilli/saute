package com.angelopicc.saute.service.impl;

import static com.angelopicc.saute.utility.message.StatusMessage.NO_RECIPES_FOUND;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Recipe;
import com.angelopicc.saute.entity.RecipeBook;
import com.angelopicc.saute.entity.ShoppingList;
import com.angelopicc.saute.exception.DeleteFailedException;
import com.angelopicc.saute.exception.NoRecipesFoundException;
import com.angelopicc.saute.payload.RecipeDto;
import com.angelopicc.saute.repository.RecipeBookRepository;
import com.angelopicc.saute.repository.RecipeRepository;
import com.angelopicc.saute.repository.ShoppingListRepository;
import com.angelopicc.saute.service.RecipeService;
import static com.angelopicc.saute.utility.message.StatusMessage.DELETE_SUCCESSFUL;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StandardRecipeService implements RecipeService {

    private RecipeRepository recipeRepository;
    private RecipeBookRepository recipeBookRepository;
    private ShoppingListRepository shoppingListRepository;

    public StandardRecipeService(RecipeRepository recipeRepository, RecipeBookRepository recipeBookRepository, 
    ShoppingListRepository shoppingListRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeBookRepository = recipeBookRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public RecipeDto createRecipe(RecipeDto recipe, long recipeBookId) {
        // 1. check if recipe book exists
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findById(recipeBookId);
        checkRecipeBookExists(optRecipeBook, "Recipe book with id: \"" + recipeBookId + "\", cannot be found");
        // 2. map dto to entity
        Recipe recipeEntity = mapToEntity(recipe);
        // 3. add recipe book to recipe entity
        recipeEntity.setRecipeBook(optRecipeBook.get());
        // 4. save recipe to db
        Recipe savedRecipe = recipeRepository.save(recipeEntity);
        return mapToDto(savedRecipe);
    }

    @Override
    public RecipeDto addRecipeToShoppingList(long recipeId, long shoppingListId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(shoppingListId);
        checkRecipeExists(optRecipe, "Recipe with id: \"" + recipeId + "\", cannot be found");
        checkShoppingListExists(optShoppingList, "Shopping list with id: \"" + shoppingListId + "\", cannot be found");

        Recipe recipe = optRecipe.get();
        ShoppingList list = optShoppingList.get();
        recipe.addShoppingList(list);
        Recipe savedRecipe = recipeRepository.save(recipe);

        return mapToDto(savedRecipe);
    }

    @Override
    public RecipeDto getRecipeById(long recipeId) {
        // 1. check for recipe exists
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        checkRecipeExists(optRecipe, "Recipe with id: \"" + recipeId + "\", cannot be found");
        // 2. map and return recipe

        return mapToDto(optRecipe.get());
    }

    @Override
    public RecipeDto getRecipeByName(String recipeName, long recipeBookId) {
        // 1. check if both recipe and recipe book exists
        Optional<RecipeBook> optRecipeBook = recipeBookRepository.findById(recipeBookId);
        checkRecipeBookExists(optRecipeBook, "Recipe book with id: \"" + recipeBookId + "\", cannot be found");
        Optional<Recipe> optRecipe = recipeRepository.findByRecipeName(recipeName);
        checkRecipeExists(optRecipe, "Recipe with name: \"" + recipeName + "\", cannot be found");
        // 2. 
        Recipe recipe = optRecipe.get();
        // 3. add recipe book to recipe entity
        recipe.setRecipeBook(optRecipeBook.get());
        // 4. save recipe to db and return mapped entity
        Recipe savedRecipe = recipeRepository.save(recipe);

        return mapToDto(savedRecipe);
    }

    @Override
    public List<RecipeDto> getAllRecipes(long recipeBookId) {
        Optional<RecipeBook> recipeBook = recipeBookRepository.findById(recipeBookId);
        checkRecipeBookExists(recipeBook, "Recipe book with id: \"" + recipeBookId + "\", cannot be found");
        RecipeBook book = recipeBook.get();

        List<Recipe> recipes = book.getRecipes();
        if (recipes.isEmpty() || recipes == null) {
            throw new NoRecipesFoundException(NO_RECIPES_FOUND);
        }

        return mapListToDtos(recipes);
    }

    @Override
    public RecipeDto updateRecipe(RecipeDto newRecipe, long oldRecipeId) {
        Optional<Recipe> oldRecipe = recipeRepository.findById(oldRecipeId);
        checkRecipeExists(oldRecipe, "Recipe with id: \"" + oldRecipeId + "\", cannot be found");

        Recipe recipe = oldRecipe.get();
        recipe.setRecipeName(newRecipe.getRecipeName());
        recipe.setDescription(newRecipe.getDescription());

        Recipe updatedRecipe = recipeRepository.save(recipe);
        return mapToDto(updatedRecipe);
    }

    @Override
    public String deleteRecipe(long recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        checkRecipeExists(recipe, "Recipe with id: \"" + recipeId + "\", cannot be found");

        recipeRepository.deleteById(recipeId);
        Optional<Recipe> deletedEntity = recipeRepository.findById(recipeId);
        if (deletedEntity.isPresent()) {
            throw new DeleteFailedException();
        }
        return DELETE_SUCCESSFUL;
    }

    // ----------------------------------------------------------------------------------------------|

    // ----------------------------------------------------------------------------------------------|

    private void checkRecipeExists(Optional<Recipe> recipe, String failMessage) {
        if (!recipe.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private void checkRecipeBookExists(Optional<RecipeBook> recipeBook, String failMessage) {
        if (!recipeBook.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private void checkShoppingListExists(Optional<ShoppingList> shoppingList, String failMessage) {
        if (!shoppingList.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private List<RecipeDto> mapListToDtos(List<Recipe> recipes) {
        return recipes.stream()
                .map(recipe -> {
                    RecipeDto dto = mapToDto(recipe);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private Recipe mapToEntity(RecipeDto dto) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(dto.getRecipeName());
        recipe.setDescription(dto.getDescription());
        return recipe;
    }
    
    private RecipeDto mapToDto(Recipe entity) {
        return new RecipeDto(entity.getId(), entity.getRecipeName(), entity.getDescription());
    }
}
