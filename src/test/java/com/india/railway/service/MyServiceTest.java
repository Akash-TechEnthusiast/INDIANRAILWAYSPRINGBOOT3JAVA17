package com.india.railway.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.india.railway.IndianRailway.IndianRailwayApplication;
import com.india.railway.model.User;
import com.india.railway.model.UserProfile;
import com.india.railway.repository.UserRepository;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = IndianRailwayApplication.class)
public class MyServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetData() {

        UserProfile uf = new UserProfile();
        uf.setEmail("test");
        User abc = new User("india", "india", uf);

        when(userRepository.save(abc)).thenReturn(abc);
        assertEquals(userService.saveUser(abc), abc);
    }
}
