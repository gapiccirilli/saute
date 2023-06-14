package com.angelopicc.saute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.Recipe;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
    Optional<Recipe> findByRecipeName(String recipeName);
}
