package com.india.railway.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.india.railway.IndianRailway.IndianRailwayApplication;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = IndianRailwayApplication.class)
public class UserControllerTest {

    /*
     * @Autowired
     * private MockMvc mockMvc;
     * 
     * @Test
     * public void testGreetUser() throws Exception {
     * mockMvc.perform(get("/user/greet").param("name", "Alice"))
     * .andExpect(status().isOk())
     * .andExpect(content().string("Hello, Alice"));
     * 
     * }
     */
}
