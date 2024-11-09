
package com.india.railway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";

    }

    @GetMapping("/login")
    public String index(Model model) {
        model.addAttribute("name", "User");
        return "login"; // Thymeleaf looks for src/main/resources/templates/index.html
    }

    @GetMapping("/authenticate")
    public String addUsers(Model model) {
        model.addAttribute("name", "Authenticate User");
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("name", "Test User");
        return "index";
    }

    @GetMapping("/loginafter")
    public String loginafter(Model model) {
        model.addAttribute("name", "loginafter");
        return "index";
    }

}
