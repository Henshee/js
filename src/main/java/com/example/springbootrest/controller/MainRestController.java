package com.example.springbootrest.controller;

import com.example.springbootrest.exception.UserNotFoundException;
import com.example.springbootrest.model.User;
import com.example.springbootrest.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainRestController {

    private final UserService userService;

    @Autowired
    public MainRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        if (userList != null && !userList.isEmpty()) {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/showUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID = " + id + " not found!");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<User> showUserInfo(@AuthenticationPrincipal User user) throws NotFoundException {
        User userById = userService.loadUserByUsername(user.getEmail());
        return ResponseEntity.ok(userById);
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws NotFoundException {
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> removeUser(@PathVariable("id") long id) throws UserNotFoundException {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
