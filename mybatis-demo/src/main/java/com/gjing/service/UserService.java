package com.gjing.service;

import com.gjing.entity.User;
import com.gjing.repository.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gjing
 **/
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 保存用户
     * @param userName 用户名
     * @param userAge 年龄
     * @return int
     */
    public int saveUser(String userName, Integer userAge) {
        return userMapper.saveUser(userName, userAge);
    }

    /**
     * 查询用户列表
     * @return userList
     */
    public List<User> listUser() {
        return userMapper.findAll();
    }
}
