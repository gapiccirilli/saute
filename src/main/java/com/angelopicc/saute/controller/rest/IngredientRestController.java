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

import com.angelopicc.saute.payload.IngredientDto;
import com.angelopicc.saute.service.IngredientService;


@RestController
@RequestMapping("/api/ingredients")
public class IngredientRestController {
    
    private IngredientService ingredientService;

    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<IngredientDto> createIngredient(@RequestBody IngredientDto ingredient) {

        return new ResponseEntity<>(ingredientService.createIngredient(ingredient), HttpStatus.CREATED);
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable long ingredientId) {

        return new ResponseEntity<>(ingredientService.getIngredientById(ingredientId), HttpStatus.OK);
    }

    @GetMapping("/names/")
    public ResponseEntity<IngredientDto> getIngredientByName(@RequestParam String ingredientName) {
        return new ResponseEntity<IngredientDto>(ingredientService.getIngredientByName(ingredientName), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
    }

    @PutMapping("/{oldIngredientId}")
    public ResponseEntity<IngredientDto> updateIngredient(@RequestBody IngredientDto newIngredient, @PathVariable long oldIngredientId) {
        return new ResponseEntity<>(ingredientService.updateIngredient(newIngredient, oldIngredientId), HttpStatus.OK);
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<String> deleteIngredient(@PathVariable long ingredientId) {
        return new ResponseEntity<>(ingredientService.deleteIngredient(ingredientId), HttpStatus.OK);
    }
}
