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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angelopicc.saute.payload.ShoppingListDto;
import com.angelopicc.saute.service.ShoppingListService;

@CrossOrigin
@RestController
@RequestMapping("/api/shopping-lists")
public class ShoppingListRestController {
    
    private ShoppingListService shoppingListService;

    public ShoppingListRestController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    public ResponseEntity<ShoppingListDto> createShoppingList(@RequestBody ShoppingListDto shoppingList) {

        return new ResponseEntity<>(shoppingListService.createShoppingList(shoppingList), HttpStatus.CREATED);
    }

    @GetMapping("/{shoppingListId}")
    public ResponseEntity<ShoppingListDto> getShoppingListById(@PathVariable long shoppingListId) {

        return new ResponseEntity<>(shoppingListService.getShoppingListById(shoppingListId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ShoppingListDto>> getShoppingListsByName(@RequestParam String shoppingListName) {
        return new ResponseEntity<>(shoppingListService.getShoppingListsByName(shoppingListName), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListDto>> getAllShoppingLists() {
        return new ResponseEntity<>(shoppingListService.getAllShoppingLists(), HttpStatus.OK);
    }

    @PutMapping("/{oldShoppingListId}")
    public ResponseEntity<ShoppingListDto> updateShoppingList(@RequestBody ShoppingListDto newShoppingList, @PathVariable long oldShoppingListId) {
        return new ResponseEntity<>(shoppingListService.updateShoppingList(newShoppingList, oldShoppingListId), HttpStatus.OK);
    }

    @DeleteMapping("/{shoppingListId}")
    public ResponseEntity<String> deleteShoppingList(@PathVariable long shoppingListId) {
        return new ResponseEntity<>(shoppingListService.deleteShoppingList(shoppingListId), HttpStatus.OK);
    }
}
