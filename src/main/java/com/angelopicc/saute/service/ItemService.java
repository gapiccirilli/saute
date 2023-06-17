package com.angelopicc.saute.service;

import java.util.List;

import com.angelopicc.saute.payload.ItemDto;

public interface ItemService {
    
   ItemDto createItemForRecipe(ItemDto item, long recipeId);
   ItemDto createItemForShoppingList(ItemDto item, long shoppingListId);
   List<ItemDto> createItemsForRecipe(List<ItemDto> items, long recipeId);
   List<ItemDto> createItemsForShoppingList(List<ItemDto> items, long shoppingListId);
   List<ItemDto> getAllItemsByRecipe(long recipeId);
   List<ItemDto> getAllItemsByShoppingList(long shoppingListId);
   ItemDto updateItem(ItemDto newItem, long oldItemId);
   String deleteItem(long itemId);
}
