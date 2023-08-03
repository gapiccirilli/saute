package com.angelopicc.saute.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angelopicc.saute.payload.ItemDto;
import com.angelopicc.saute.service.ItemService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ItemRestController {
    
    private ItemService itemService;

    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/recipes/{recipeId}/items")
    public ResponseEntity<ItemDto> createItemForRecipe(@RequestBody ItemDto item, @PathVariable long recipeId) {
        return new ResponseEntity<ItemDto>(itemService.createItemForRecipe(item, recipeId), HttpStatus.CREATED);
    }

    @PostMapping("/shopping-lists/{shoppingListId}/items")
    public ResponseEntity<ItemDto> createItemForShoppingList(@RequestBody ItemDto item, @PathVariable long shoppingListId) {
        return new ResponseEntity<>(itemService.createItemForShoppingList(item, shoppingListId), HttpStatus.CREATED);
    }

    @PostMapping("/recipes/{recipeId}/items/multiple")
    public ResponseEntity<List<ItemDto>> createItemsForRecipe(@RequestBody List<ItemDto> items, @PathVariable long recipeId) {
        return new ResponseEntity<>(itemService.createItemsForRecipe(items, recipeId), HttpStatus.CREATED);
    }

    @PostMapping("/shopping-lists/{shoppingListId}/items/multiple")
    public ResponseEntity<List<ItemDto>> createItemsForShoppingList(@RequestBody List<ItemDto> items, 
    @PathVariable long shoppingListId) {
        return new ResponseEntity<>(itemService.createItemsForShoppingList(items, shoppingListId), HttpStatus.CREATED);
    }

    @GetMapping("/recipes/{recipeId}/items/multiple")
    public ResponseEntity<List<ItemDto>> getAllItemsByRecipe(@PathVariable long recipeId) {
        return new ResponseEntity<>(itemService.getAllItemsByRecipe(recipeId), HttpStatus.OK);
    }

    @GetMapping("/shopping-lists/{shoppingListId}/items/multiple")
    public ResponseEntity<List<ItemDto>> getAllItemsByShoppingList(@PathVariable long shoppingListId) {
        return new ResponseEntity<>(itemService.getAllItemsByShoppingList(shoppingListId), HttpStatus.OK);
    }

    @PutMapping("/items/{oldItemId}")
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto newItem, @PathVariable long oldItemId) {
        return new ResponseEntity<>(itemService.updateItem(newItem, oldItemId), HttpStatus.OK);
    }

    @DeleteMapping("items/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable long itemId) {
        return new ResponseEntity<>(itemService.deleteItem(itemId), HttpStatus.OK);
    }
}
