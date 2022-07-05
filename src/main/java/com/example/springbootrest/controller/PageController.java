package com.example.springbootrest.controller;

import com.example.springbootrest.model.Role;
import com.example.springbootrest.model.User;
import com.example.springbootrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api")
public class PageController {

    private final UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", Role.values());
        return "main";
    }

    @GetMapping("/user")
    public String showUser(@AuthenticationPrincipal User userModel, Model model) {
        User user = userService.loadUserByUsername(userModel.getFirstName());
        model.addAttribute("user", user);
        return "userInfo";
    }

//    @GetMapping("/user/{id}")
//    public String showUserById(@PathVariable("id") long id, Model model) {
//        User user = userService.getUser(id);
//        model.addAttribute("user", user);
//        return "userInfo";
//    }
}
