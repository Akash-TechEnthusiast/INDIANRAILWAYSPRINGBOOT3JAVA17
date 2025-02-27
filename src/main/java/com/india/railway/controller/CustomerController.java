package com.india.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.Customer;
import com.india.railway.service.CustomerService;

@RequestMapping(path = "/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/users")
    public @ResponseBody Iterable<Customer> getAllUsers() {
        // This returns a JSON or XML with the Book
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Customer createUser(@RequestBody Customer user) {
        return customerService.addCustomer(user);
    }

    @GetMapping("/{id}")
    public Customer getUserById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

}