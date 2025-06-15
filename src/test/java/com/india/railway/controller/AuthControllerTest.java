package com.india.railway.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.india.railway.authservice.JwtUtils;
import com.india.railway.model.mysql.User;
import com.india.railway.repository.mysql.UserRepository;
import com.india.railway.service.mysql.UserServiceTestImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest // ✅ Loads entire Spring context
// (properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
// @WebMvcTest(EntryController.class) // ✅ Loads only the controller, NOT the
// entire Spring context
class AuthControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserServiceTestImpl userService; // Mocking the service layer

        @Autowired
        private ObjectMapper objectMapper; // Converts Java object to JSON

        @MockBean
        private UserRepository userRepository;

        @MockBean
        private AuthenticationManager authenticationManager;

        @MockBean
        private JwtUtils jwtUtil;

        @WithMockUser(username = "testuser", roles = "USER")
        @Test
        public void testregister() throws Exception {
                // Arrange
                User mockUser = new User();
                mockUser.setId(1L);
                mockUser.setUserName("Alice");
                mockUser.setEmail("alice@example.com");
                mockUser.setPassword("google1234");
                // mockUser.SET("+917075459707");

                when(userRepository.save(any(User.class))).thenReturn(mockUser);
                // Act & Assert
                mockMvc.perform(post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(mockUser)))

                                .andExpect(status().isOk())
                                .andExpect(content().string("User registered successfully"));
        }

}
