package ua.com.tarvic.javaspring.security.basicdb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.tarvic.javaspring.security.basicdb.models.Customer;
import ua.com.tarvic.javaspring.security.basicdb.services.CustomerService;

@RestController
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("/save")
    public void save(@RequestBody Customer customer) {
        customerService.save(customer);
    }

    @GetMapping("/admin")
    public String findAllAdmin() {
        return "admin controller";
    }

    @GetMapping("/user")
    public String findAllUsers() {
        return "user controller";
    }

}
