package com.gj.service;

import com.gj.domain.User;
import com.gj.repository.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gjing
 **/
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public Integer saveUser(User user) {
        return userDao.saveUser(user);
    }

    public User findById(Integer id) {
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
