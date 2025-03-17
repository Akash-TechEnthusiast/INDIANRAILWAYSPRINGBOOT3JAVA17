package com.india.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.Student_Mysql;
import com.india.railway.service.StudentService_Mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
// Allow React Frontend
public class StudentController_Mysql {

    @Autowired
    private StudentService_Mysql studentservice_mysql;

    @PostMapping("/create")
    public Student_Mysql addStudent(@RequestBody Student_Mysql student) {
        return studentservice_mysql.addStudent(student);
    }

    /*
     * public List<Customer_Mysql> getAllCustomers() {
     * return customerService.getAllCustomers();
     * 
     * }
     * 
     */

    @GetMapping("/fetch_all_students")
    public List<Map<String, Object>> getStudentDropdown() {
        return studentservice_mysql.getAllStudent().stream().map(student -> {
            Map<String, Object> map = new HashMap<>();
            // map.put("id", student.getId());
            map.put("name", student.getName());
            return map;
        }).collect(Collectors.toList());
    }

    /*
     * @GetMapping("/{id}")
     * public Optional<Customer_Mysql> getCustomerById(@PathVariable Long id) {
     * return customerService.getCustomerById(id);
     * }
     */

    @PutMapping("/{id}")
    public Student_Mysql updateStudent(@PathVariable Long id, @RequestBody Student_Mysql student) {
        return studentservice_mysql.updateStuent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentservice_mysql.deleteStudent(id);
        return "Customer deleted successfully!";
    }
}
