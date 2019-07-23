package com.gj.repository;

import com.gj.domain.User;

import java.util.List;

/**
 * @author Gjing
 **/
public interface UserDao {
    Integer saveUser(User user);

    User findById(Integer id);

    List<User> findAll();
}
