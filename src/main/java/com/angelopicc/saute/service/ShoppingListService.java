package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.ShoppingListDto;

public interface ShoppingListService {
    
    ShoppingListDto createShoppingList(ShoppingListDto shoppingList);
    ShoppingListDto getShoppingListById(long shoppingListId);
    List<ShoppingListDto> getShoppingListByName(String shoppingListName);
    List<ShoppingListDto> getAllShoppingLists();
    ShoppingListDto updateShoppingList(ShoppingListDto newShoppingList, long oldShoppingListId);
    String deleteShoppingList(long shoppingListId);
}