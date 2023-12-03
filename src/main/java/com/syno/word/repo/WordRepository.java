package com.syno.word.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.syno.word.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
   
    @Query("SELECT DISTINCT w FROM Word w LEFT JOIN FETCH w.synonyms WHERE w.id = :wordId")
    List<Word> findWordWithSynonymsById(Long wordId);
}
