package com.syno.word.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Synonym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    @ElementCollection
    private List<String> synonyms;

    // Getters and setters
}
