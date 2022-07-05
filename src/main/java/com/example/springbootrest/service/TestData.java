package com.example.springbootrest.service;

import com.example.springbootrest.model.Role;
import com.example.springbootrest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class TestData {
    UserService userDetailsService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public TestData(UserService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void test() {
        User user1 = new User(passwordEncoder.encode("2002"), "Nick", "Anderson", 17, "AndersonTest@test.com", Role.ROLE_ADMIN);
        userDetailsService.save(user1);
    }
}
