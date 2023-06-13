package com.angelopicc.saute.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angelopicc.saute.payload.RecipeBookDto;
import com.angelopicc.saute.service.RecipeBookService;

@RestController
@RequestMapping("/api/recipebooks")
public class RecipeBookRestController {

    private RecipeBookService recipeBookService;

    public RecipeBookRestController(RecipeBookService recipeBookService) {
        this.recipeBookService = recipeBookService;
    }
    
    @PostMapping
    public ResponseEntity<RecipeBookDto> createRecipeBook(@RequestBody RecipeBookDto recipeBook) {

        return new ResponseEntity<>(recipeBookService.createRecipeBook(recipeBook), HttpStatus.CREATED);
    }

    @GetMapping("/{recipeBookId}")
    public ResponseEntity<RecipeBookDto> getRecipeBookById(@PathVariable long recipeBookId) {

        return new ResponseEntity<>(recipeBookService.getRecipeBookById(recipeBookId), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<RecipeBookDto> getRecipeBookByName(@RequestParam String recipeBookName) {
        return new ResponseEntity<RecipeBookDto>(recipeBookService.getRecipeBookByName(recipeBookName), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecipeBookDto>> getAllRecipeBooks() {
        return new ResponseEntity<>(recipeBookService.getAllRecipeBooks(), HttpStatus.OK);
    }

    @PutMapping("/{oldRecipeBookId}")
    public ResponseEntity<RecipeBookDto> updateRecipeBook(@RequestBody RecipeBookDto newRecipeBook, @PathVariable long oldRecipeBookId) {
        return new ResponseEntity<>(recipeBookService.updateRecipeBook(newRecipeBook, oldRecipeBookId), HttpStatus.OK);
    }

    @DeleteMapping("/{recipeBookId}")
    public ResponseEntity<String> deleteRecipeBook(@PathVariable long recipeBookId) {
        return new ResponseEntity<>(recipeBookService.deleteRecipeBook(recipeBookId), HttpStatus.OK);
    }
}
