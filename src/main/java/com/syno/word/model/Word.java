package com.syno.word.model;

import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Word {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String word;

    @Column(name="synonyms")
    private Set<Long> synonyms = new TreeSet<Long>();

}

