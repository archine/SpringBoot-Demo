package com.gjing.repository;

import com.gjing.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Gjing
 **/
@Mapper
public interface UserMapper {
    /**
     * 保存用户
     * @param userName 用户名
     * @param userAge 年龄
     * @return int
     */
    @Insert("insert into user (user_name,user_age,create_time) values(#{userName},#{userAge},now())")
    int saveUser(String userName, Integer userAge);

    /**
     * 查询所有用户
     * @return userList
     */
    List<User> findAll();
}
