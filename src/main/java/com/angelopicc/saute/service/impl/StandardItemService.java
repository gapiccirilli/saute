package com.angelopicc.saute.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Item;
import com.angelopicc.saute.entity.Measurement;
import com.angelopicc.saute.entity.Recipe;
import com.angelopicc.saute.entity.ShoppingList;
import com.angelopicc.saute.payload.ItemDto;
import com.angelopicc.saute.repository.ItemRepository;
import com.angelopicc.saute.repository.MeasurementRepository;
import com.angelopicc.saute.repository.RecipeRepository;
import com.angelopicc.saute.repository.ShoppingListRepository;
import com.angelopicc.saute.service.ItemService;
import com.angelopicc.saute.service.MeasurementService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StandardItemService implements ItemService {

    private ItemRepository itemRepository;
    private RecipeRepository recipeRepository;
    private ShoppingListRepository shoppingListRepository;
    private MeasurementService measurementService;
    private MeasurementRepository measurementRepository;
 
    

    public StandardItemService(ItemRepository itemRepository, RecipeRepository recipeRepository,
            ShoppingListRepository shoppingListRepository, MeasurementService measurementService,
            MeasurementRepository measurementRepository) {
        this.itemRepository = itemRepository;
        this.recipeRepository = recipeRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.measurementService = measurementService;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public ItemDto createItemForRecipe(ItemDto item, long recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        checkRecipeExists(optRecipe, "Recipe with id: \"" + recipeId + "\", cannot be found");
        Item itemEntity = mapToEntity(item);

        Measurement measurement = measurementService.createMeasurement(item.getAmount(), item.getMeasurementType());
        itemEntity.setRecipe(optRecipe.get());
        itemEntity.setMeasurement(measurement);

        Item savedItem = itemRepository.save(itemEntity);

        return mapToDto(savedItem);
    }

    @Override
    public ItemDto createItemForShoppingList(ItemDto item, long shoppingListId) {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(shoppingListId);
        checkShoppingListExists(optShoppingList, "Shopping list with id: \"" + shoppingListId + "\", cannot be found");
        Item itemEntity = mapToEntity(item);

        Measurement measurement = measurementService.createMeasurement(item.getAmount(), item.getMeasurementType());
        itemEntity.setShoppingList(optShoppingList.get());
        itemEntity.setMeasurement(measurement);

        Item savedItem = itemRepository.save(itemEntity);

        return mapToDto(savedItem);
    }

    @Override
    public List<ItemDto> createItemsForRecipe(List<ItemDto> items, long recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        checkRecipeExists(optRecipe, "Recipe with id: \"" + recipeId + "\", cannot be found");
        Recipe recipe = optRecipe.get();

        List<Item> itemList = items.stream().map(item -> {
            Item itemEntity = mapToEntity(item);
            Measurement measurement = measurementService.createMeasurement(item.getAmount(), item.getMeasurementType());
            itemEntity.setRecipe(recipe);
            itemEntity.setMeasurement(measurement);
            return itemEntity;
        }).collect(Collectors.toList());

        List<Item> savedItems = itemRepository.saveAll(itemList);

        return mapListToDto(savedItems);
    }

    @Override
    public List<ItemDto> createItemsForShoppingList(List<ItemDto> items, long shoppingListId) {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(shoppingListId);
        checkShoppingListExists(optShoppingList, "Shopping list with id: \"" + shoppingListId + "\", cannot be found");
        ShoppingList shoppingList = optShoppingList.get();

        List<Item> itemList = items.stream().map(item -> {
            Item itemEntity = mapToEntity(item);
            Measurement measurement = measurementService.createMeasurement(item.getAmount(), item.getMeasurementType());
            itemEntity.setShoppingList(shoppingList);
            itemEntity.setMeasurement(measurement);
            return itemEntity;
        }).collect(Collectors.toList());

        List<Item> savedItems = itemRepository.saveAll(itemList);

        return mapListToDto(savedItems);
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

    private void checkRecipeExists(Optional<Recipe> recipe, String failMessage) {
        if (!recipe.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private void checkShoppingListExists(Optional<ShoppingList> shoppingList, String failMessage) {
        if (!shoppingList.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private void checkItemExists(Optional<Item> item, String failMessage) {
        if (!item.isPresent()) {
            throw new EntityNotFoundException(failMessage);
        }
    }

    private Item mapToEntity(ItemDto dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setHours(dto.getHours());
        item.setMinutes(dto.getMinutes());
        item.setSeconds(dto.getSeconds());

        return item;
    }

    private ItemDto mapToDto(Item entity) {
        ItemDto dto = new ItemDto();
        dto.setId(entity.getId());
        dto.setIngredientId(entity.getIngredient().getId());
        dto.setIngredientName(entity.getIngredient().getIngredientName());
        dto.setAmount(entity.getMeasurement().getAmount());
        dto.setMeasurementType(entity.getMeasurement().getMeasurementType());
        dto.setHours(entity.getHours());
        dto.setMinutes(entity.getMinutes());
        dto.setSeconds(entity.getSeconds());
        return dto;
    }
    
    private List<Item> mapListToEntity(List<ItemDto> dtos) {

        return dtos.stream().map(dto -> mapToEntity(dto)).collect(Collectors.toList());
    }

    private List<ItemDto> mapListToDto(List<Item> entities) {
        return entities.stream().map(entity -> mapToDto(entity)).collect(Collectors.toList());
    }
}
