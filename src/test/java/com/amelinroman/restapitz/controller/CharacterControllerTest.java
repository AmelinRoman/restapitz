package com.amelinroman.restapitz.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Amelin Roman
 * Класс для тестирования CharacterController.
 */

@SpringBootTest
@AutoConfigureMockMvc
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;


    /**
     * Тест метода getCountSymbols.
     *
     * @throws Exception если произошла ошибка при выполнении запроса
     */

    @Test
    public void testGetCountSymbols() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/symbols")
                .param("input", "aaaaabcccc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.a").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.b").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.c").value(4));
    }


    /**
     * Тест метода getCountSymbols с некорректным входным параметром.
     *
     * @throws Exception если произошла ошибка при выполнении запроса
     */
    @Test
    public void testInvalidInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/symbols")
                .param("input", "abcd123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
