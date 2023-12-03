package com.syno.word.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.syno.word.model.SynonymRequest;
import com.syno.word.service.SynonymsService;

@RestController
@RequestMapping("/api")
public class SynonymsController {

    @Autowired
    private SynonymsService synonymsService;

    @PostMapping("/add")
    public ResponseEntity<String> addSynonym(@RequestBody SynonymRequest request) {
        // Implement logic to add synonyms
        return null;
    }

    // Implement other CRUD operations

    @GetMapping("/fetch/{word}")
    public ResponseEntity<List<String>> fetchSynonyms(@PathVariable String word) {
        // Implement logic to fetch synonyms
        return null;
    }
}
