package com.angelopicc.saute.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.Recipe;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
    Optional<Recipe> findByRecipeName(String recipeName);
    List<Recipe> findByRecipeNameAndRecipeBookIdStartingWith(String search, long recipeBookId);
}
