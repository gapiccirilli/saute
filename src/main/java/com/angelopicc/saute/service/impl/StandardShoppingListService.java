package com.angelopicc.saute.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.ShoppingList;
import com.angelopicc.saute.exception.DeleteFailedException;
import com.angelopicc.saute.exception.NoShoppingListsFoundException;
import com.angelopicc.saute.payload.ShoppingListDto;
import com.angelopicc.saute.repository.ShoppingListRepository;
import com.angelopicc.saute.service.ShoppingListService;
import static com.angelopicc.saute.utility.message.StatusMessage.DELETE_SUCCESSFUL;

import static com.angelopicc.saute.utility.message.StatusMessage.NO_SHOPPING_LISTS_FOUND;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StandardShoppingListService implements ShoppingListService {

    private ShoppingListRepository shoppingListRepository;

    public StandardShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ShoppingListDto createShoppingList(ShoppingListDto shoppingList) {
        ShoppingList shoppingListEntity = mapToEntity(shoppingList);
        ShoppingList savedList = shoppingListRepository.save(shoppingListEntity);

        return mapToDto(savedList);
    }

    @Override
    public ShoppingListDto getShoppingListById(long shoppingListId) {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(shoppingListId);
        checkShoppingListExists(optShoppingList, "Shoppinglist with id: \"" + shoppingListId + "\", cannot be found");
        ShoppingList shoppingList = optShoppingList.get();

        return mapToDto(shoppingList);
    }

    @Override
    public List<ShoppingListDto> getShoppingListByName(String shoppingListName) {
        // for searching
        List<ShoppingList> shoppingLists = shoppingListRepository.findByListNameStartingWith(shoppingListName);

        if (shoppingLists.isEmpty() || shoppingLists == null) {
            throw new NoShoppingListsFoundException(NO_SHOPPING_LISTS_FOUND);
        }
        return mapListToDto(shoppingLists);
    }

    @Override
    public List<ShoppingListDto> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListRepository.findAll();

        if (shoppingLists.isEmpty() || shoppingLists == null) {
            throw new NoShoppingListsFoundException(NO_SHOPPING_LISTS_FOUND);
        }
        return mapListToDto(shoppingLists);
    }

    @Override
    public ShoppingListDto updateShoppingList(ShoppingListDto newShoppingList, long oldShoppingListId) {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(oldShoppingListId);
        checkShoppingListExists(optShoppingList, "Shoppinglist with id: \"" + oldShoppingListId + "\", cannot be found");
        ShoppingList shoppingList = optShoppingList.get();

        shoppingList.setListName(newShoppingList.getListName());
        ShoppingList savedShoppingList = shoppingListRepository.save(shoppingList);

        return mapToDto(savedShoppingList);
    }

    @Override
    public String deleteShoppingList(long shoppingListId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        checkShoppingListExists(shoppingList, "Shoppinglist with id: \"" + shoppingListId + "\", cannot be found");

        shoppingListRepository.deleteById(shoppingListId);
        Optional<ShoppingList> deletedList = shoppingListRepository.findById(shoppingListId);
        if (deletedList.isPresent()) {
            throw new DeleteFailedException();
        }

        return DELETE_SUCCESSFUL;
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
