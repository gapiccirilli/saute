package com.angelopicc.saute.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.payload.ItemDto;
import com.angelopicc.saute.service.ItemService;

@Service
public class StandardItemService implements ItemService {

    @Override
    public ItemDto createItemForRecipe(ItemDto item, long recipeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createItemForRecipe'");
    }

    @Override
    public ItemDto createItemForShoppingList(ItemDto item, long shoppingListId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createItemForShoppingList'");
    }

    @Override
    public List<ItemDto> createItemsForRecipe(List<ItemDto> items, long recipeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createItemsForRecipe'");
    }

    @Override
    public List<ItemDto> createItemsForShoppingList(List<ItemDto> items, long shoppingListId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createItemsForShoppingList'");
    }

    @Override
    public List<ItemDto> getAllItemsByRecipe(long recipeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllItemsByRecipe'");
    }

    @Override
    public List<ItemDto> getAllItemsByShoppingList(long shoppingListId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllItemsByShoppingList'");
    }

    @Override
    public ItemDto updateItem(ItemDto newItem, long oldItemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItem'");
    }

    @Override
    public String deleteItem(long itemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteItem'");
    }
    
}
