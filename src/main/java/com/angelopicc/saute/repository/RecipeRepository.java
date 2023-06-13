package com.angelopicc.saute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.Recipe;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
    Recipe findByRecipeName(String recipeName);
}
