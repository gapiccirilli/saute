package com.angelopicc.saute.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Ingredient;
import com.angelopicc.saute.exception.DuplicateNameException;
import com.angelopicc.saute.exception.NoIngredientsFoundException;
import com.angelopicc.saute.payload.IngredientDto;
import com.angelopicc.saute.repository.IngredientRepository;
import com.angelopicc.saute.service.IngredientService;
import static com.angelopicc.saute.utility.error.ErrorMessage.NO_INGREDIENTS_FOUND;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StandardIngredientService implements IngredientService {

    private IngredientRepository ingredientRepository;

    public StandardIngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientDto createIngredient(IngredientDto ingredient) {
        Optional<Ingredient> optIngredient = ingredientRepository.findByIngredientName(ingredient.getIngredientName());
        if (optIngredient.isPresent()) {
            throw new DuplicateNameException("Ingredient \"" + ingredient.getIngredientName() + "\" already exists");
        }
        Ingredient ingredientEntity = mapToEntity(ingredient);
        Ingredient savedIngredient = ingredientRepository.save(ingredientEntity);

        return mapToDto(savedIngredient);
    }

    @Override
    public IngredientDto getIngredientById(long ingredientId) {
        Optional<Ingredient> optIngredient = ingredientRepository.findById(ingredientId);
        checkIngredientExists(optIngredient, "Ingredient with id: \"" + ingredientId + "\", cannot be found");

        Ingredient ingredient = optIngredient.get();

        return mapToDto(ingredient);
    }

    @Override
    public List<IngredientDto> getIngredientByName(String ingredientName) {
        // use this method/endpoint for searching through ingredients
        List<Ingredient> searchedIngredients = ingredientRepository.findByIngredientNameStartingWith(ingredientName);

        if (searchedIngredients.isEmpty() || searchedIngredients == null) {
            throw new NoIngredientsFoundException(NO_INGREDIENTS_FOUND);
        }
        return mapListToDto(searchedIngredients);
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllIngredients'");
    }

    @Override
    public IngredientDto updateIngredient(IngredientDto newIngredient, long oldIngredientId) {
        
        return null;
    }

    @Override
    public String deleteIngredient(long ingredientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteIngredient'");
    }

    // --------------------------------------------------------------------------------------------------|

    // --------------------------------------------------------------------------------------------------|

    private void checkIngredientExists(Optional<Ingredient> ingredient, String failMessage) {
        if (!ingredient.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private List<IngredientDto> mapListToDto(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> {
                    IngredientDto dto = new IngredientDto();
                    int numOfRecipes = ingredient.getItems().size();

                    dto.setId(ingredient.getId());
                    dto.setIngredientName(ingredient.getIngredientName());
                    dto.setNumberOfRecipes(numOfRecipes);
                    
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
        int numOfRecipes = entity.getItems().size();

        dto.setId(entity.getId());
        dto.setIngredientName(entity.getIngredientName());
        dto.setNumberOfRecipes(numOfRecipes);

        return dto;
    }
}
