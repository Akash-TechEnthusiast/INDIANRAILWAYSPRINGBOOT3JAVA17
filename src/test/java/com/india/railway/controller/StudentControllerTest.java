package com.india.railway.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.india.railway.model.mysql.Student_Mysql;
import com.india.railway.service.mysql.StudentService_Mysql;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// @WebMvcTest(EntryController.class) // ✅ Loads only the controller, NOT the
// entire Spring context // (properties = "spring.profiles.active=test")
@SpringBootTest // ✅ Loads entire Spring context
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService_Mysql userService_mysql; // Mocking the service layer

    @Autowired
    private ObjectMapper objectMapper; // Converts Java object to JSON

    @Test
    public void testCreateUserTest() throws Exception {
        // Arrange
        Student_Mysql inputStudent = new Student_Mysql();

        inputStudent.setAge(0);
        inputStudent.setCountry("india");

        Student_Mysql returnStudent = new Student_Mysql();

        returnStudent.setAge(0);
        returnStudent.setCountry("india");

        when(userService_mysql.addStudent(any(Student_Mysql.class))).thenReturn(returnStudent);

        // Act & Assert
        mockMvc.perform(post("/api/student/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputStudent)))
                .andExpect(jsonPath("$.age").value(0))
                .andExpect(jsonPath("$.country").value("india"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // Arrange: mock list of users
        List<Student_Mysql> studentlist = Arrays.asList(
                new Student_Mysql(1L, 10, "india"),
                new Student_Mysql(2L, 20, "pakistan"));

        when(userService_mysql.getAllStudent()).thenReturn(studentlist);

        // Act & Assert
        mockMvc.perform(get("/api/student/fetch_all_students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("india"))
                .andExpect(jsonPath("$[1].name").value("pakistan"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        // Given
        Long userId = 1L;

        Student_Mysql inputStudent = new Student_Mysql();

        inputStudent.setAge(0);
        inputStudent.setCountry("india");

        Student_Mysql returnStudent = new Student_Mysql();

        returnStudent.setAge(10);
        returnStudent.setCountry("USA");

        when(userService_mysql.updateStudent(eq(userId), any(Student_Mysql.class))).thenReturn(returnStudent);

        // When & Then
        mockMvc.perform(put("/api/student/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(10))
                .andExpect(jsonPath("$.country").value("USA"));
    }

    @Test
    void testDeleteStudent() throws Exception {

        Long studentId = 1L;

        // Mocking the void method
        doNothing().when(userService_mysql).deleteStudent(studentId);

        mockMvc.perform(delete("/api/student/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted successfully!"));

    }

}
