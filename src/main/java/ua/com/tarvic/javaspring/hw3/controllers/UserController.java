package ua.com.tarvic.javaspring.hw3.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ua.com.tarvic.javaspring.hw3.models.User;
import ua.com.tarvic.javaspring.hw3.models.dto.UserDTO;
import ua.com.tarvic.javaspring.hw3.models.views.Views;
import ua.com.tarvic.javaspring.hw3.services.UserService;
import ua.com.tarvic.javaspring.hw3.utils.UserUtil;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserUtil userUtil;

    public UserController(
        @Qualifier("hw3.UserService.v1")
        UserService userService,
        UserUtil userUtil
    ) {
        this.userService = userService;
        this.userUtil = userUtil;
    }

    //get /users
    @GetMapping()
    @JsonView(Views.Public.class)
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    //get /users/{id}
    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<User> findById(@PathVariable int id) {
        User user = userService.findById(id);
        HttpStatus status = user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(user, status);
    }

    //post /users
    @SneakyThrows
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @JsonView(Views.Public.class)
    public ResponseEntity<User> save(/*@RequestBody */@Valid UserDTO userDTO) {
        User user = userUtil.convertDtoToEntity(userDTO);
        User saved;

        MultipartFile avatar = userDTO.getAvatar();
        if (avatar != null) {
            String originalFilename = avatar.getOriginalFilename();
            user.setAvatar(originalFilename);

            String path = System.getProperty("user.dir") + File.separator + "files" + File.separator + originalFilename;
            File transferDestinationFile = new File(path);
            avatar.transferTo(transferDestinationFile);
            saved = userService.save(user, transferDestinationFile);
        } else {
            saved = userService.save(user);
        }

        return new ResponseEntity<>(saved,
                userDTO.getId() == 0 ? HttpStatus.CREATED : HttpStatus.OK);
    }

    //delete /users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        Boolean deleted = userService.deleteById(id);
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status.getReasonPhrase(), status);
    }
    
    
////////////////////////////////////////////////////////////////////////////////////////    

    @SneakyThrows
    @PostMapping("/savewithavatar")
    public void saveWithAvatar(@RequestParam("avatar") MultipartFile avatar,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        String originalFilename = avatar.getOriginalFilename(); // homer.jpg
        user.setAvatar(originalFilename); // {name:kokos,avatar:homer.jpg}

//        System.out.println(avatar.getResource().getFile().getPath());

        String path = System.getProperty("user.dir") + File.separator + "files" + File.separator + originalFilename;
        File transferDestinationFile = new File(path);
        avatar.transferTo(transferDestinationFile);
        userService.save(user, transferDestinationFile);
    }


    @GetMapping("/activate/{id}")
    public void activateUser(@PathVariable int id) {
        User user = userService.findById(id);
        user.setActivated(true);
        userService.save(user);
    }

}