package com.angelopicc.saute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.Ingredient;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    
    Ingredient findByIngredientName(String ingredientName);
}
