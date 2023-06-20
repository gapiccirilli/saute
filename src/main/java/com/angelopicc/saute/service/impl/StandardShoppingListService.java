package com.angelopicc.saute.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.ShoppingList;
import com.angelopicc.saute.payload.ShoppingListDto;
import com.angelopicc.saute.service.ShoppingListService;

import jakarta.persistence.EntityNotFoundException;

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

    private void checkShoppingListExists(Optional<ShoppingList> shoppingList, String failMessage) {
        if (!shoppingList.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }
    
    private ShoppingList mapToEntity(ShoppingListDto dto) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(dto.getId());
        shoppingList.setListName(dto.getListName());
        return shoppingList;
    }

    private ShoppingListDto mapToDto(ShoppingList entity) {
        ShoppingListDto dto = new ShoppingListDto(entity.getId(), entity.getListName());
        return dto;
    }

    private List<ShoppingList> mapListToEntity(List<ShoppingListDto> dtos) {
        return dtos.stream().map(dto -> mapToEntity(dto)).collect(Collectors.toList());
    }

    private List<ShoppingListDto> mapListToDto(List<ShoppingList> entities) {
        return entities.stream().map(entity -> mapToDto(entity)).collect(Collectors.toList());
    }
}
