package com.example.springbootrest.dao;

import com.example.springbootrest.model.User;
import java.security.Principal;
import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> index();

    User getUser(Long id);

    void delete(Long id);

    void update(User user);

    User getUserByName(String name);

    boolean isAllowed(Long id, Principal principal);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
