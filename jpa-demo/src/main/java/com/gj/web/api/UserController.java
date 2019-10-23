package com.gj.web.api;

import cn.gjing.tools.common.annotation.NotNull;
import cn.gjing.tools.common.result.PageResult;
import com.gj.domain.User;
import com.gj.domain.dto.UserDto;
import com.gj.domain.vo.UserVO;
import com.gj.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gjing
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user")
    @ApiOperation(value = "增加用户", httpMethod = "POST")
    @NotNull
    public ResponseEntity saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.ok("添加成功");
    }

    @DeleteMapping("/user/{user_id}")
    @ApiOperation(value = "删除用户", httpMethod = "DELETE")
    public ResponseEntity deleteUser(@PathVariable("user_id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("删除成功");
    }

    @GetMapping("/user_page")
    @ApiOperation(value = "分页查询用户列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数（0开始）", required = true, dataType = "int", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, dataType = "int", paramType = "query", defaultValue = "5")
    })
    @NotNull
    public ResponseEntity<PageResult<List<UserVO>>> listForUser(Integer page, Integer size) {
        return ResponseEntity.ok(userService.listForUser(PageRequest.of(page, size, Sort.Direction.DESC,"id")));
    }

    @GetMapping("/user/{user_phone}")
    @ApiOperation(value = "根据手机号查询", httpMethod = "GET")
    public ResponseEntity<User> findUser(@PathVariable("user_phone") String userPhone) {
        return ResponseEntity.ok(userService.findByUserPhone(userPhone));
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户信息", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "long", paramType = "Query"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "Query")
    })
    @NotNull
    public ResponseEntity updateUser(Long userId, String userName) {
        userService.updateUser(userName, userId);
        return ResponseEntity.ok("更新成功");
    }

    @GetMapping("/user_list")
    @ApiOperation(value = "动态查询用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAge", value = "用户年龄", dataType = "int", paramType = "Query"),
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String", paramType = "Query")
    })
    public ResponseEntity<List<User>> userList(Integer userAge, String userName) {
        return ResponseEntity.ok(userService.dynamicFind(userAge, userName));
    }
}
