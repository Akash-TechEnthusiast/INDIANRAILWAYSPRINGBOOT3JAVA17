package com.india.railway.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

//@WebMvcTest(RestControllerClass.class)
@SpringBootTest(classes = RestControllerClass.class)
// @AutoConfigureMockMvc
// @SpringBootTest
@AutoConfigureMockMvc
public class RestControllerClassTest {

    @Autowired
    private MockMvc mockMvc;

    // @MockBean
    // private UserService userService;

    @Test
    public void testGreetUser() throws Exception {
        mockMvc.perform(get("/api/users/1")) // Simulates GET request
                .andExpect(status().isOk()) // Expects HTTP 200 status
                .andExpect(content().string("Hello, User 1")); // Expects specific response content
    }

}
