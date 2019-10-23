package com.gj.repository;

import com.gj.domain.User;
import com.gj.domain.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gjing
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {
    /**
     * 通过手机号查询
     * @param userPhone 手机号
     * @return user
     */
    User findByUserPhone(String userPhone);
    /**
     * 根据用户名分页查询
     * @param userName 用户名
     * @param pageable 分页对象
     * @return page
     */
    Page<User> findByUserName(String userName, Pageable pageable);

    /**
     * 根据用户id更新用户
     * @param userId 用户id
     * @param userName 用户名
     */
    @Query("update User u set u.userName = ?1 where u.id = ?2")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void updateById(String userName, Long userId);

    /**
     * 查询指定用户
     * @param userPhone 用户号码
     * @return user
     */
    @Query("select u from User u where u.userPhone = ?1")
    User findUserByUserPhone(String userPhone);

    /**
     * 分页查询用户
     * @param pageable 分页条件
     * @return Page<UserVO>
     */
    @Query("select new com.gj.domain.vo.UserVO(u.id,u.userName,u.userAge,u.userPhone) from User as u")
    Page<UserVO> findAllUser(Pageable pageable);
}
