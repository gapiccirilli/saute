package com.angelopicc.saute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.RecipeBook;

public interface RecipeBookRepository extends JpaRepository<RecipeBook, Long> {
    
    RecipeBook findByRecipeBookName(String recipeBookName);
}
