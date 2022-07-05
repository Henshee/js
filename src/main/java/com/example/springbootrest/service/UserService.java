package com.example.springbootrest.service;

import com.example.springbootrest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    List<User> index();

    User getUser(Long id);

    void delete(Long id);

    void update(User user);

    User getUserByName(String name);

    boolean isAllowed(Long id, Principal principal);

    User loadUserByUsername(String email) throws UsernameNotFoundException;

    List<User> getAllUsers();
}
