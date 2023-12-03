package com.syno.word.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.syno.word.model.Word;
import com.syno.word.repo.WordRepository;

@Service
public class WordService {

    private final WordRepository wordRepository;

    // Constructor injection of WordRepository
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    // Add a new word
    public Word addWord(Word word) {
        return wordRepository.save(word);
    }

    // Update an existing word
    public Word updateWord(Long id, Word updatedWord) throws NotFoundException {
        Word existingWord = wordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

         // Update properties of existingWord with updatedWord
         if (updatedWord.getWord() != null) {
            existingWord.setWord(updatedWord.getWord());
        }
        // Add more properties to update as needed...

        return wordRepository.save(existingWord);
    }

    // Delete a word by its ID
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }

    // Fetch a word by its ID
    public Word getWordById(Long id) throws NotFoundException {
        return wordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    // Assign synonyms to a word
    public Word assignSynonyms(Long id,Long id2) throws NotFoundException {
        Word existingWord = wordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        Set<Long> synonyms = new TreeSet<Long>();
        if(existingWord.getSynonyms()!=null)
        synonyms.addAll(existingWord.getSynonyms());
        synonyms.add(id2);
        existingWord.setSynonyms(synonyms);
        return wordRepository.save(existingWord);
    }

    // Fetch synonyms of a word
    public List<Word> getSynonymsOfWord(Long id) throws NotFoundException {
        Word word=wordRepository.findById(id).orElseThrow(() -> new NotFoundException());
        List<Word> words=null;

        if(word.getSynonyms()==null)
        return words;
        words=wordRepository.findAllById(word.getSynonyms());
        Set<Long> synonyms = new TreeSet<Long>();
        synonyms.addAll(word.getSynonyms());
        for(Long s:synonyms){
            List<Word> wd=this.getSynonymsOfWord(s);
            if(wd!=null)
            words.addAll(wd); 
        }        
        return words;
    }

    // Fetch synonyms of a word
    public Set<String> getSynonymsWord(Long id) throws NotFoundException {
        Word word=wordRepository.findById(id).orElseThrow(() -> new NotFoundException());
        Set<String> syns=new TreeSet<String>();

        if(word.getSynonyms()==null)
        return syns;

        List<Word> words=wordRepository.findAllById(word.getSynonyms());
        words.forEach(e->syns.add(e.getWord()));
        Set<Long> synonyms = new TreeSet<Long>();
        synonyms.addAll(word.getSynonyms());
        for(Long s:synonyms){
            List<Word> wd=this.getSynonymsOfWord(s);
            if(wd!=null)
            wd.forEach(e->syns.add(e.getWord()));
        }        
        return syns;
    }
}
