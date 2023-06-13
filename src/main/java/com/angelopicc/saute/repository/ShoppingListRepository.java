package com.angelopicc.saute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    ShoppingList findByListName(String listName);
}
