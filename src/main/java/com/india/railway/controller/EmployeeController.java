package com.india.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.mysql.Employee;
import com.india.railway.service.mysql.EmployeeServiceImpl;

@RequestMapping(path = "/employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/getAll")
    public @ResponseBody Iterable<Employee> getAllUsers() {
        // This returns a JSON or XML with the Book
        return employeeServiceImpl.getAllEmployee();
    }

    @PostMapping(path = "/create")
    public String createUser(@RequestBody Employee user) {
        return employeeServiceImpl.addEmployee(user);
    }

    @GetMapping("/{id}")
    public Employee getUserById(@PathVariable Long id) {
        return employeeServiceImpl.getEmployee(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeServiceImpl.deleteEmployee(id);
    }

    @PutMapping("/update")
    public void UpdateEmployee(@RequestBody Employee employee) {

        employeeServiceImpl.updateEmployee(employee);
    }

}