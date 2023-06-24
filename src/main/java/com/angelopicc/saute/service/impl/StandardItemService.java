package com.angelopicc.saute.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.Ingredient;
import com.angelopicc.saute.entity.Item;
import com.angelopicc.saute.entity.Measurement;
import com.angelopicc.saute.entity.Recipe;
import com.angelopicc.saute.entity.ShoppingList;
import com.angelopicc.saute.exception.DeleteFailedException;
import com.angelopicc.saute.exception.NoItemsFoundException;
import com.angelopicc.saute.payload.ItemDto;
import com.angelopicc.saute.repository.IngredientRepository;
import com.angelopicc.saute.repository.ItemRepository;
import com.angelopicc.saute.repository.RecipeRepository;
import com.angelopicc.saute.repository.ShoppingListRepository;
import com.angelopicc.saute.service.ItemService;
import com.angelopicc.saute.service.MeasurementService;

import jakarta.persistence.EntityNotFoundException;
import static com.angelopicc.saute.utility.message.StatusMessage.DELETE_SUCCESSFUL;
import static com.angelopicc.saute.utility.message.StatusMessage.NO_ITEMS_FOUND;

@Service
public class StandardItemService implements ItemService {

    private ItemRepository itemRepository;
    private RecipeRepository recipeRepository;
    private ShoppingListRepository shoppingListRepository;
    private MeasurementService measurementService;
    private IngredientRepository ingredientRepository;
 
    public StandardItemService(ItemRepository itemRepository, RecipeRepository recipeRepository,
            ShoppingListRepository shoppingListRepository, MeasurementService measurementService,
             IngredientRepository ingredientRepository) {
        this.itemRepository = itemRepository;
        this.recipeRepository = recipeRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.measurementService = measurementService;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public ItemDto createItemForRecipe(ItemDto item, long recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        Optional<Ingredient> optIngredient = ingredientRepository.findById(item.getIngredientId());
        checkRecipeExists(optRecipe, "Recipe with id: \"" + recipeId + "\", cannot be found");
        checkIngredientExists(optIngredient, "Ingredient with id: \"" + item.getIngredientId() + "\", cannot be found");
        Item itemEntity = mapToEntity(item);

        Measurement measurement = measurementService.createMeasurement(item.getAmount(), item.getMeasurementType());
        itemEntity.setRecipe(optRecipe.get());
        itemEntity.setIngredient(optIngredient.get());
        itemEntity.setMeasurement(measurement);

        Item savedItem = itemRepository.save(itemEntity);

        return mapToDto(savedItem);
    }

    @Override
    public ItemDto createItemForShoppingList(ItemDto item, long shoppingListId) {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(shoppingListId);
        Optional<Ingredient> optIngredient = ingredientRepository.findById(item.getIngredientId());
        checkShoppingListExists(optShoppingList, "Shopping list with id: '" + shoppingListId + "', cannot be found");
        checkIngredientExists(optIngredient, "Ingredient with id: \"" + item.getIngredientId() + "\", cannot be found");
        Item itemEntity = mapToEntity(item);

        Measurement measurement = measurementService.createMeasurement(item.getAmount(), item.getMeasurementType());
        itemEntity.setShoppingList(optShoppingList.get());
        itemEntity.setIngredient(optIngredient.get());
        itemEntity.setMeasurement(measurement);

        Item savedItem = itemRepository.save(itemEntity);

        return mapToDto(savedItem);
    }

    @Override
    public List<ItemDto> createItemsForRecipe(List<ItemDto> items, long recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        checkRecipeExists(optRecipe, "Recipe with id: '" + recipeId + "', cannot be found");
        Recipe recipe = optRecipe.get();

        List<Item> itemList = items.stream().map(item -> {
            Optional<Ingredient> optIngredient = ingredientRepository.findById(item.getIngredientId());
            checkIngredientExists(optIngredient, "Ingredient with id: \"" + item.getIngredientId() + "\", cannot be found");

            Item itemEntity = mapToEntity(item);
            Measurement measurement = measurementService.createMeasurement(item.getAmount(), item.getMeasurementType());
            itemEntity.setRecipe(recipe);
            itemEntity.setIngredient(optIngredient.get());
            itemEntity.setMeasurement(measurement);
            return itemEntity;
        }).collect(Collectors.toList());

        List<Item> savedItems = itemRepository.saveAll(itemList);

        return mapListToDto(savedItems);
    }

    @Override
    public List<ItemDto> createItemsForShoppingList(List<ItemDto> items, long shoppingListId) {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(shoppingListId);
        checkShoppingListExists(optShoppingList, "Shopping list with id: '" + shoppingListId + "', cannot be found");
        ShoppingList shoppingList = optShoppingList.get();

        List<Item> itemList = items.stream().map(item -> {
            Optional<Ingredient> optIngredient = ingredientRepository.findById(item.getIngredientId());
            checkIngredientExists(optIngredient, "Ingredient with id: \"" + item.getIngredientId() + "\", cannot be found");

            Item itemEntity = mapToEntity(item);
            Measurement measurement = measurementService.createMeasurement(item.getAmount(), item.getMeasurementType());
            itemEntity.setShoppingList(shoppingList);
            itemEntity.setIngredient(optIngredient.get());
            itemEntity.setMeasurement(measurement);
            return itemEntity;
        }).collect(Collectors.toList());

        List<Item> savedItems = itemRepository.saveAll(itemList);

        return mapListToDto(savedItems);
    }

    @Override
    public List<ItemDto> getAllItemsByRecipe(long recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        checkRecipeExists(optRecipe, "Recipe with id: '" + recipeId + "', cannot be found");

        Recipe recipe = optRecipe.get();
        
        List<Item> items = recipe.getItems();
        if (items.isEmpty() || items == null) {
            throw new NoItemsFoundException(NO_ITEMS_FOUND);
        }

        return mapListToDto(items);
    }

    @Override
    public List<ItemDto> getAllItemsByShoppingList(long shoppingListId) {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(shoppingListId);
        checkShoppingListExists(optShoppingList, "Shopping list with id: '" + shoppingListId + "', cannot be found");

        ShoppingList shoppingList = optShoppingList.get();

        List<Item> items = shoppingList.getItems();
        if (items.isEmpty() || items == null) {
            throw new NoItemsFoundException(NO_ITEMS_FOUND);
        }
        return mapListToDto(shoppingList.getItems());
    }

    @Override
    public ItemDto updateItem(ItemDto newItem, long oldItemId) {
        Optional<Item> oldItem = itemRepository.findById(oldItemId);
        checkItemExists(oldItem, "Item with id: '" + oldItemId + "', cannot be found");
        Item item = oldItem.get();

        Measurement newMeasurement = item.getMeasurement();
        newMeasurement.setAmount(newItem.getAmount());
        newMeasurement.setMeasurementType(newItem.getMeasurementType());

        item.setMeasurement(newMeasurement);
        item.setDescription(newItem.getDescription());
        item.setHours(newItem.getHours());
        item.setMinutes(newItem.getMinutes());
        item.setSeconds(newItem.getSeconds());
        
        if (item.getIngredient().getId() != newItem.getIngredientId()) {
            Optional<Ingredient> optIngredient = ingredientRepository.findById(newItem.getIngredientId());
            if (!optIngredient.isPresent()) {
                throw new EntityNotFoundException("Ingredient with id: '" + newItem.getIngredientId() + "', cannot be found");
            }
            Ingredient newIngredient = optIngredient.get();
            item.setIngredient(newIngredient);
        }
        Item savedItem = itemRepository.save(item);

        return mapToDto(savedItem);
    }

    @Override
    public String deleteItem(long itemId) {
        Optional<Item> optItem = itemRepository.findById(itemId);
        checkItemExists(optItem, "Item with id: '" + itemId + "', cannot be found");

        itemRepository.deleteById(itemId);
        Optional<Item> deletedItem = itemRepository.findById(itemId);
        if (deletedItem.isPresent()) {
            throw new DeleteFailedException();
        }

        return DELETE_SUCCESSFUL;
    }

    // ----------------------------------------------------------------------------------------|

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

    private void checkIngredientExists(Optional<Ingredient> ingredient, String failMessage) {
        if (!ingredient.isPresent()) {
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
        item.setDescription(dto.getDescription());
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
        dto.setDescription(entity.getDescription());
        dto.setAmount(entity.getMeasurement().getAmount());
        dto.setMeasurementType(entity.getMeasurement().getMeasurementType());
        dto.setHours(entity.getHours());
        dto.setMinutes(entity.getMinutes());
        dto.setSeconds(entity.getSeconds());
        return dto;
    }

    private List<ItemDto> mapListToDto(List<Item> entities) {
        return entities.stream().map(entity -> mapToDto(entity)).collect(Collectors.toList());
    }
}
