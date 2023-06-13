package com.angelopicc.saute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.RecipeBook;

public interface RecipeBookRepository extends JpaRepository<RecipeBook, Long> {
    
    Optional<RecipeBook> findByRecipeBookName(String recipeBookName);
}
