package com.angelopicc.saute.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angelopicc.saute.service.impl.GlobalSearchService;

@CrossOrigin
@RestController
@RequestMapping("/api/search")
public class SearchRestController {
    
    private GlobalSearchService searchService;

    public SearchRestController(GlobalSearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public ResponseEntity<List<Object>> globalSearch(@RequestParam String query) {
        return new ResponseEntity<>(searchService.search(query), HttpStatus.OK);
    }

}
