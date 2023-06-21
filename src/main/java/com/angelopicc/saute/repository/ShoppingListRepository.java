package com.angelopicc.saute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    List<ShoppingList> findByShoppingListNameStartingWith(String search);
}
