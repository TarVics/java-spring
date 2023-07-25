package ua.com.tarvic.javaspring.security.basic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MainController {

    @GetMapping("/admin")
    public String findAllAdmin() {
        return "admin controller";
    }

    @GetMapping("/users")
    public String findAllUsers() {
        return "user controller";
    }

}
