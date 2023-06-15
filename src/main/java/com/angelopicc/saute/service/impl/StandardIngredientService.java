package com.angelopicc.saute.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Ingredient;
import com.angelopicc.saute.entity.Measurement;
import com.angelopicc.saute.entity.Recipe;
import com.angelopicc.saute.entity.ShoppingList;
import com.angelopicc.saute.payload.IngredientDto;
import com.angelopicc.saute.repository.IngredientRepository;
import com.angelopicc.saute.repository.MeasurementRepository;
import com.angelopicc.saute.repository.RecipeRepository;
import com.angelopicc.saute.repository.ShoppingListRepository;
import com.angelopicc.saute.service.IngredientService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StandardIngredientService implements IngredientService {

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private ShoppingListRepository shoppingListRepository;
    private MeasurementRepository measurementRepository;

    public StandardIngredientService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository,
            ShoppingListRepository shoppingListRepository, MeasurementRepository measurementRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public IngredientDto createIngredient(IngredientDto ingredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createIngredient'");
    }

    @Override
    public IngredientDto addIngredientToRecipe(IngredientDto ingredient, long recipeId) {
        // Using ingredient dto instead of ingredientId so that measurement data can be retrieved as well
        // 1. get entity from ingredient and check that it exists and create new Measurement
        Optional<Ingredient> optIngredient = ingredientRepository.findById(ingredient.getId());
        checkIngredientExists(optIngredient, "Ingredient with id: \"" + ingredient.getId() + "\", cannot be found");
        Ingredient ingredientEntity = optIngredient.get();

        
        // 2. get recipe entity and add it to both measurement and ingredient
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        checkRecipeExists(optRecipe, "Recipe with id: \"" + recipeId + "\", cannot be found");
        Recipe recipe = optRecipe.get();

        // 3. make ingredientName exactly the same for measurement as it is in ingredient
        Measurement measurement = new Measurement();
        measurement.setIngredientName(ingredientEntity.getIngredientName());
        measurement.setAmount(ingredient.getAmount());
        measurement.setMeasurementType(ingredient.getMeasurmentType());
        measurement.setRecipe(recipe);

        ingredientEntity.addRecipe(recipe);
        
        // 4. persist both
        Ingredient savedIngredient = ingredientRepository.save(ingredientEntity);
        Measurement savedMeasurement = measurementRepository.save(measurement);

        return mapToDto(savedIngredient, savedMeasurement);
    }

    @Override
    public IngredientDto addIngredientToShoppingList(long ingredientId, long shoppingListId) {
        return null;
    }

    @Override
    public IngredientDto getIngredientById(long ingredientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIngredientById'");
    }

    @Override
    public IngredientDto getIngredientByName(String ingredientName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIngredientByName'");
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllIngredients'");
    }

    @Override
    public List<IngredientDto> getAllIngredientsByRecipe(long recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        checkRecipeExists(optRecipe, "Recipe with id: \"" + recipeId + "\", cannot be found");

        Recipe recipe = optRecipe.get();
        List<Measurement> measurements = recipe.getMeasurements();
        List<Ingredient> ingredients = recipe.getIngredients();

        return mapListToDto(ingredients, measurements);
    }

    @Override
    public List<IngredientDto> getAllIngredientsByShoppingList(long shoppingListId) {
        return null;
    }

    @Override
    public IngredientDto updateIngredient(IngredientDto newIngredient, long oldIngredientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateIngredient'");
    }

    @Override
    public String deleteIngredient(long ingredientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteIngredient'");
    }

    private void checkIngredientExists(Optional<Ingredient> ingredient, String failMessage) {
        if (!ingredient.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private void checkRecipeExists(Optional<Recipe> recipe, String failMessage) {
        if (!recipe.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private void checkMeasurementExists(Optional<Ingredient> measurement, String failMessage) {
        if (!measurement.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private List<IngredientDto> mapListToDto(List<Ingredient> ingredients, List<Measurement> measurements) {
        return ingredients.stream()
                .map(ingredient -> {
                    IngredientDto dto = new IngredientDto();
                    dto.setId(ingredient.getId());
                    dto.setIngredientName(ingredient.getIngredientName());
                    dto.setNumberOfRecipes(ingredient.getRecipes().size());

                    for (Measurement measurement : measurements) {
                        if (ingredient.getIngredientName().equals(measurement.getIngredientName())) {
                            dto.setAmount(measurement.getAmount());
                            dto.setMeasurmentType(measurement.getMeasurementType());
                            break;
                        }
                    }
                    return dto;
                })
                .collect(Collectors.toList());         
    }
    
    private Ingredient mapToEntity(IngredientDto dto) {
        Ingredient entity = new Ingredient();
        entity.setId(dto.getId());
        entity.setIngredientName(dto.getIngredientName());

        return entity;
    }

    private IngredientDto mapToDto(Ingredient entity) {
        IngredientDto dto = new IngredientDto();
        int numOfRecipes = entity.getRecipes().size();

        dto.setId(entity.getId());
        dto.setIngredientName(entity.getIngredientName());
        dto.setNumberOfRecipes(numOfRecipes);

        return dto;
    }

    private IngredientDto mapToDto(Ingredient entity1, Measurement entity2) {
        IngredientDto dto = new IngredientDto();
        int numOfRecipes = entity1.getRecipes().size();

        dto.setId(entity1.getId());
        dto.setIngredientName(entity1.getIngredientName());
        dto.setNumberOfRecipes(numOfRecipes);
        dto.setAmount(entity2.getAmount());
        dto.setMeasurmentType(entity2.getMeasurementType());

        return dto;
    }
}
