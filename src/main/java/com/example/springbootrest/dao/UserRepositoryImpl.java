package com.example.springbootrest.dao;

import com.example.springbootrest.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.Principal;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(User user) {
        em.persist(user);
        em.flush();
    }


    @Override
    public List<User> index() {
        return em.createQuery("select u from User u").getResultList();
    }


    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public void update(User updatedUser) {
        em.merge(updatedUser);
        em.flush();
    }


    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.firstName = ?1", User.class);
        query.setParameter(1, name);
        return query.getSingleResult();
    }

    @Override
    public boolean isAllowed(Long id, Principal principal) {
        User user = getUserByName(principal.getName());
        return user.getId() == id || user.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().contains("ADMIN"));
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.email = '" + email +"'" , User.class);
        return query.getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }
}
