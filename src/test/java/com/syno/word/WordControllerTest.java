package com.syno.word;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syno.word.controller.WordController;
// import com.syno.word.model.Synonym;
import com.syno.word.model.Word;
import com.syno.word.service.WordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WordController.class)
public class WordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordService wordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddWord() throws Exception {
        Word word = new Word();
        word.setId(1L);
        word.setWord("TestWord");

        when(wordService.addWord(any(Word.class))).thenReturn(word);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/words")
                .content(objectMapper.writeValueAsString(word))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.word").value("TestWord"));

        verify(wordService, times(1)).addWord(any(Word.class));
    }

    @Test
    public void testGetWordById() throws Exception {
        Word word = new Word();
        word.setId(1L);
        word.setWord("TestWord");

        when(wordService.getWordById(1L)).thenReturn(word);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/words/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.word").value("TestWord"));

        verify(wordService, times(1)).getWordById(anyLong());
    }

    @Test
    public void testAssignSynonyms() throws Exception {
        // Synonym word = new Synonym();
        // word.setId(1L);
        // word.setWords({4L});

        // when(wordService.assignSynonyms(word)).thenReturn(word);

        // mockMvc.perform(MockMvcRequestBuilders.post("/api/words/1/assign-synonyms")
        //         .content(objectMapper.writeValueAsString(Collections.singletonList(2L)))
        //         .contentType(MediaType.APPLICATION_JSON))
        //         .andExpect(status().isOk())
        //         .andExpect(jsonPath("$.id").value(1))
        //         .andExpect(jsonPath("$.word").value("TestWord"));

        // verify(wordService, times(1)).assignSynonyms(anyLong(), anyList());
    }

    @Test
    public void testGetSynonymsOfWord() throws Exception {
        Word synonym = new Word();
        synonym.setId(2L);
        synonym.setWord("SynonymWord");

        //List<Word> synonyms = Collections.singletonList(synonym);

        //when(wordService.getSynonymsOfWord(1L)).thenReturn(synonyms);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/words/1/synonyms")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].word").value("SynonymWord"));

        verify(wordService, times(1)).getSynonymsOfWord(anyLong());
    }

}
