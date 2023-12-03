package com.syno.word.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.syno.word.model.Word;
import com.syno.word.service.WordService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/")
public class WordController {

    private final WordService wordService;

    // Constructor injection of WordService
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/home")
    public String homePage() {
        return "Welcome Word Synonyms Registry";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "Error In Login Please Retry Again ! Comtact Administrator !";
    }

    // Add a new word
    @PostMapping
    public ResponseEntity<Word> addWord(@RequestBody Word word) {
        word.setSynonyms(null);
        Word savedWord = wordService.addWord(word);
        return new ResponseEntity<>(savedWord, HttpStatus.CREATED);
    }

    // Update an existing word
    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable Long id, @RequestBody Word word) throws NotFoundException {
        Word updatedWord = wordService.updateWord(id, word);
        return new ResponseEntity<>(updatedWord, HttpStatus.OK);
    }

    // Delete a word by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Fetch a word by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable Long id) throws NotFoundException {
        Word word = wordService.getWordById(id);
        return new ResponseEntity<>(word, HttpStatus.OK);
    }

    // Assign synonyms to a word
    @PostMapping("/assign-synonyms/{id}={id2}")
    public ResponseEntity<Word> assignSynonyms(@PathVariable Long id,@PathVariable Long id2) throws NotFoundException {       
        Word word=wordService.assignSynonyms(id,id2);
        return new ResponseEntity<>(word,HttpStatus.OK);
    }

    // Fetch synonyms of a word
    @GetMapping("/{id}/synonyms")
    public ResponseEntity<List<Word>> getSynonymsOfWord(@PathVariable Long id) throws NotFoundException {
        List<Word> synonyms = wordService.getSynonymsOfWord(id);
        return new ResponseEntity<>(synonyms, HttpStatus.OK);
    }

     // Fetch synonyms of a word
    @GetMapping("/{id}/synonym")
    public ResponseEntity<Set<String>> getSynonymsWord(@PathVariable Long id) throws NotFoundException {
        Set<String> synonyms = wordService.getSynonymsWord(id);
        return new ResponseEntity<>(synonyms, HttpStatus.OK);
    }

    // @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        // Create a custom error response
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Something went wrong");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


