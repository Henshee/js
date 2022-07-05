package com.example.springbootrest.service;

import com.example.springbootrest.dao.UserRepository;
import com.example.springbootrest.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository dao;

    @Autowired
    public UserServiceImpl(UserRepository dao) {
        this.dao = dao;
    }

    public void save(User user) {
        dao.save(user);
    }

    public List<User> index() {
        return dao.index();
    }

    public User getUser(Long id) {
        return dao.getUser(id);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public void update(User user) {
        dao.update(user);
    }

    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }

    public boolean isAllowed(Long id, Principal principal) {
        return dao.isAllowed(id, principal);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @SneakyThrows
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = dao.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with e-mail: %s not found!", email));
        }
        return user;
    }
}
