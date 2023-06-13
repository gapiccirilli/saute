package com.angelopicc.saute.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.payload.ShoppingListDto;
import com.angelopicc.saute.service.ShoppingListService;

@Service
public class StandardShoppingListService implements ShoppingListService {

    @Override
    public ShoppingListDto createShoppingList(ShoppingListDto shoppingList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createShoppingList'");
    }

    @Override
    public ShoppingListDto getShoppingListById(long shoppingListId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShoppingListById'");
    }

    @Override
    public ShoppingListDto getShoppingListByName(String shoppingListName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShoppingListByName'");
    }

    @Override
    public List<ShoppingListDto> getAllShoppingLists() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllShoppingLists'");
    }

    @Override
    public ShoppingListDto updateShoppingList(ShoppingListDto newShoppingList, long oldShoppingListId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateShoppingList'");
    }

    @Override
    public String deleteShoppingList(long shoppingListId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShoppingList'");
    }
    
}
