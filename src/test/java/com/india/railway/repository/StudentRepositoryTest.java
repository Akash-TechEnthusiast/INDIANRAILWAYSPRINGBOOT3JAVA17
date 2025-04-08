package com.india.railway.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import com.india.railway.model.Student_Mysql;
import com.india.railway.model.User;
import com.india.railway.service.StudentService_Mysql;

//@DataJpaTest
@SpringBootTest
class StudentRepositoryTest {

    @MockBean
    private Student_Repo_Mysql studentRepository;

    @Autowired
    private StudentService_Mysql userService_mysql;

    @Test
    void testfetchAllStudents() {

        List<Student_Mysql> studentlist = Arrays.asList(
                new Student_Mysql(1L, 10, "india"),
                new Student_Mysql(2L, 20, "pakistan"));

        when(studentRepository.findAll()).thenReturn(studentlist);

        List<Student_Mysql> saved = userService_mysql.getAllStudent();

        assertEquals(2, saved.size());
        // assertEquals("john@example.com", saved.getEmail());
    }

    @Test
    public void testGetUserById() throws Exception {
        // Arrange

        Student_Mysql student = new Student_Mysql(1L, 10, "india");

        when(studentRepository.findById(12L)).thenReturn(Optional.of(student));
        Optional<Student_Mysql> saved = userService_mysql.getStudentById(12L);

        // Assert
        assertTrue(saved.isPresent());
        assertEquals("india", saved.get().getCountry());

    }

    @Test
    public void testAddStudent() throws Exception {
        // Arrange

        Student_Mysql instudent = new Student_Mysql(1L, 10, "india");

        Student_Mysql outstudent = new Student_Mysql(1L, 10, "india");

        when(studentRepository.save(instudent)).thenReturn(outstudent);
        Student_Mysql saved = userService_mysql.addStudent(instudent);

        // Assert
        assertEquals("india", saved.getCountry());

    }

    @Test
    public void testUpdateStudent() throws Exception {
        // Arrange

        Student_Mysql instudent = new Student_Mysql(1L, 10, "india");
        Student_Mysql outstudent = new Student_Mysql(2L, 20, "usa");

        when(studentRepository.findById(12L)).thenReturn(Optional.of(instudent));
        when(studentRepository.save(instudent)).thenReturn(outstudent);
        Student_Mysql saved = userService_mysql.updateStudent(12L, outstudent);

        // Assert
        assertNotNull(saved);
        assertEquals("usa", saved.getCountry());

    }

    @Test
    void testDeleteStudent() throws Exception {

        Long studentId = 1L;

        // Mocking the void method
        doNothing().when(studentRepository).deleteById(studentId);
        userService_mysql.deleteStudent(studentId);
        verify(studentRepository, times(1)).deleteById(studentId);

    }

}