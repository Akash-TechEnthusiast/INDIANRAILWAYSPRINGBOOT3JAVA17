package com.india.railway.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.india.railway.model.User;
import com.india.railway.service.UserServiceTestImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
// @WebMvcTest(EntryController.class) // ✅ Loads only the controller, NOT the
// entire Spring context
class EntryControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserServiceTestImpl userService; // Mocking the service layer

        @Autowired
        private ObjectMapper objectMapper; // Converts Java object to JSON

        @Test
        public void testGetAllUsers() throws Exception {
                // Arrange: mock list of users
                List<User> users = Arrays.asList(
                                new User(1L, "Alice", "alice@example.com"),
                                new User(2L, "Bob", "bob@example.com"));

                when(userService.getAllUsers()).thenReturn(users);

                // Act & Assert
                mockMvc.perform(get("/userstest/list"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.length()").value(2))
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].username").value("Alice"))
                                .andExpect(jsonPath("$[0].email").value("alice@example.com"))
                                .andExpect(jsonPath("$[1].id").value(2))
                                .andExpect(jsonPath("$[1].username").value("Bob"))
                                .andExpect(jsonPath("$[1].email").value("bob@example.com"));
        }

        @Test
        void testGetUser() throws Exception {
                mockMvc.perform(get("/userstest/getId/1"))
                                .andExpect(status().isOk())
                                .andExpect(content().string("User with ID: 1"));
        }

        @Test
        public void testFileUpload() throws Exception {
                // Arrange: create a fake file
                MockMultipartFile mockFile = new MockMultipartFile(
                                "file", // name of the param expected by @RequestParam
                                "test.txt", // original filename
                                "text/plain", // content type
                                "Hello, world!".getBytes() // content
                );

                // Act & Assert
                mockMvc.perform(multipart("/userstest/upload")
                                .file(mockFile))
                                .andExpect(status().isOk())
                                .andExpect(content().string("File uploaded: test.txt"));
        }

        @Test
        public void testCreateUserTest() throws Exception {
                // Arrange
                User inputUser = new User();
                inputUser.setUsername("Alice");
                inputUser.setEmail("alice@example.com");

                User savedUser = new User();
                savedUser.setId(1L);
                savedUser.setUsername("Alice");
                savedUser.setEmail("alice@example.com");

                when(userService.saveUsertest(any(User.class))).thenReturn(savedUser);

                // Act & Assert
                mockMvc.perform(post("/userstest/save1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(inputUser)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(1L))
                                .andExpect(jsonPath("$.username").value("Alice"))
                                .andExpect(jsonPath("$.email").value("alice@example.com"));
        }

        @Test
        public void testGetUserById() throws Exception {
                // Arrange
                User mockUser = new User();
                mockUser.setId(1L);
                mockUser.setUsername("Alice");
                mockUser.setEmail("alice@example.com");

                when(userService.getUserById(1L)).thenReturn(mockUser);

                // Act & Assert
                mockMvc.perform(get("/userstest/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.username").value("Alice"))
                                .andExpect(jsonPath("$.email").value("alice@example.com"));
        }

}
