package com.angelopicc.saute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.Ingredient;
import java.util.List;



public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    
    Optional<Ingredient> findByIngredientName(String ingredientName);
    List<Ingredient> findByIngredientNameStartingWith(String ingredientName);
}
