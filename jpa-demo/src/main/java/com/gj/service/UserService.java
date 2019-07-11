package com.gj.service;

import cn.gjing.ParamUtil;
import cn.gjing.result.PageResult;
import com.gj.domain.User;
import com.gj.repository.UserRepository;
import com.gj.web.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @author Gjing
 **/
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    /**
     * 保存用户
     * @param userDto 用户传输对象
     * @return t / f
     */
    public boolean saveUser(UserDto userDto) {
        User userDb = userRepository.findByUserPhone(userDto.getUserPhone());
        if (userDb == null) {
            User user = userRepository.saveAndFlush(User.builder().userName(userDto.getUserName())
                    .userAge(userDto.getUserAge())
                    .userPhone(userDto.getUserPhone())
                    .build());
            return user != null;
        }
        return false;
    }

    /**
     * 分页查询用户列表
     * @param pageable 分页条件
     * @return PageResult
     */
    public PageResult listForUser(Pageable pageable) {
        List<Map<String, Object>> data = new LinkedList<>();
        Page<User> userPage = userRepository.findAll(pageable);
        userPage.getContent().forEach(e->{
            Map<String,Object> map = new HashMap<>(16);
            map.put("userId", e.getId());
            map.put("userName", e.getUserName());
            map.put("userAge", e.getUserAge());
            map.put("userPhone", e.getUserPhone());
            data.add(map);
        });
        return PageResult.of(data, userPage.getTotalPages());
    }

    /**
     * 删除用户
     * @param userId 用户id
     */
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(u -> userRepository.delete(u));
    }

    /**
     * 更新用户
     * @param userName 用户名
     * @param userId 用户id
     * @return 更新数量
     */
    public Integer updateUser(String userName,Long userId) {
        return userRepository.updateById(userName, userId);
    }

    /**
     * 根据手机号查询
     * @param userPhone 手机号
     * @return user
     */
    public User findByUserPhone(String userPhone) {
        return userRepository.findUserByUserPhone(userPhone);
    }

    /**
     * 动态查询
     * @param age 岁数
     * @param userName 用户名
     * @return list
     */
    public List<User> dynamicFind(Integer age, String userName) {
        Specification<User> specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (ParamUtil.isNotEmpty(age)) {
                predicateList.add(criteriaBuilder.greaterThan(root.get("userAge"), age));
            }
            if (ParamUtil.isNotEmpty(userName)) {
                predicateList.add(criteriaBuilder.equal(root.get("userName"), userName));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
        return userRepository.findAll(specification);
    }
}
