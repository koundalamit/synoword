package com.syno.word.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syno.word.model.Synonym;

public interface SynonymsRepository extends JpaRepository<Synonym, Long> {
    // Define custom queries if needed
}

