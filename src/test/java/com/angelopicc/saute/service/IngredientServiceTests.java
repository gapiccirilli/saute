package com.angelopicc.saute.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.angelopicc.saute.entity.Ingredient;
import com.angelopicc.saute.payload.IngredientDto;
import com.angelopicc.saute.repository.IngredientRepository;
import com.angelopicc.saute.service.IngredientService;
import com.angelopicc.saute.service.impl.StandardIngredientService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class IngredientServiceTests {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private StandardIngredientService ingredientService;

    private Ingredient ingredient1;

    private Ingredient ingredient2;

    private Ingredient mappedIngredient;

    private IngredientDto dto1;

    private IngredientDto dto2;

    private IngredientDto mappedDto;

    @BeforeEach
    public void setUp() {
        ingredient1 = new Ingredient(1, "Tomato", null, null);
        ingredient2 = new Ingredient(2, "Potato", null, null);
        mappedIngredient = mapToEntity(dto1);
        dto1 = new IngredientDto(1, "Tomato", 0);
        dto1 = new IngredientDto(2, "Potato", 0);
        mappedDto = mapToDto(ingredient1);
    }
    
    @Test
    void testCreateIngredient() {

        // given - preconditon
        BDDMockito.given(ingredientRepository.findByIngredientName(dto1.getIngredientName())).willReturn(Optional.empty());
        BDDMockito.given(ingredientRepository.save(mappedIngredient)).willReturn(mappedIngredient);
        // when - test behavior
        IngredientDto result = ingredientService.createIngredient(dto1);
        // then - verify output
        Assertions.assertThat(result).isEqualTo(dto1);
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
