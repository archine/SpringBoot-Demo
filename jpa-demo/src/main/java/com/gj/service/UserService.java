package com.gj.service;

import cn.gjing.tools.common.result.PageResult;
import cn.gjing.tools.common.util.ParamUtils;
import com.gj.domain.User;
import com.gj.domain.dto.UserDto;
import com.gj.domain.vo.UserVO;
import com.gj.exception.ServiceException;
import com.gj.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gjing
 **/
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    /**
     * 保存用户
     *
     * @param userDto 用户传输对象
     */
    public void saveUser(UserDto userDto) {
        User userDb = userRepository.findByUserPhone(userDto.getUserPhone());
        if (userDb != null) {
            throw new ServiceException("用户已存在");
        }
        User user = userRepository.saveAndFlush(User.builder().userName(userDto.getUserName())
                .userAge(userDto.getUserAge())
                .userPhone(userDto.getUserPhone())
                .build());
    }

    /**
     * 分页查询用户列表
     *
     * @param pageable 分页条件
     * @return PageResult
     */
    public PageResult<List<UserVO>> listForUser(Pageable pageable) {
        Page<UserVO> userPage = userRepository.findAllUser(pageable);
        return PageResult.of(userPage.getContent(), userPage.getTotalPages());
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(u -> userRepository.delete(u));
    }

    /**
     * 更新用户
     *
     * @param userName 用户名
     * @param userId   用户id
     */
    public void updateUser(String userName, Long userId) {
        userRepository.updateById(userName, userId);
    }

    /**
     * 根据手机号查询
     *
     * @param userPhone 手机号
     * @return user
     */
    public User findByUserPhone(String userPhone) {
        return userRepository.findUserByUserPhone(userPhone);
    }

    /**
     * 动态查询
     *
     * @param age      岁数
     * @param userName 用户名
     * @return list
     */
    public List<User> dynamicFind(Integer age, String userName) {
        Specification<User> specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (ParamUtils.isNotEmpty(age)) {
                predicateList.add(criteriaBuilder.equal(root.get("userAge"), age));
            }
            if (ParamUtils.isNotEmpty(userName)) {
                predicateList.add(criteriaBuilder.equal(root.get("userName"), userName));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
        return userRepository.findAll(specification);
    }
}
