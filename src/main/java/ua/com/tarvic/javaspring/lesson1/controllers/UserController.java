package ua.com.tarvic.javaspring.lesson1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.tarvic.javaspring.lesson1.dao.UserDAO;
import ua.com.tarvic.javaspring.lesson1.models.User;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/lesson1/users")
public class UserController {


    //    @Autowired /* = find in bean container instance of userDAO and insert here*/
    // To avoid adding @Autowired you need add constructor with "UserDAO userDAO" parameter
    // To avoid adding constructor with "UserDAO userDAO" tou need add annotation @AllArgsConstructor
    private UserDAO userDAO;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userDAO.save(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("A-IM", "custom value");
        //return new ResponseEntity<>(userDAO.findAll(), httpHeaders, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(userDAO.findAll(), httpHeaders, HttpStatusCode.valueOf(202));
    }


    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") int userId) {
        Optional<User> byId = userDAO.findById(userId);
        return byId.orElse(null);
    }


    @DeleteMapping()
    public List<User> deleteUser(@RequestParam("id") int userId) {
        userDAO.deleteById(userId);
        return userDAO.findAll();

    }

}