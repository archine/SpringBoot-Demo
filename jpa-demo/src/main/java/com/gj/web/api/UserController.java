package com.gj.web.api;

import cn.gjing.annotation.NotNull;
import cn.gjing.result.PageResult;
import com.gj.service.UserService;
import com.gj.web.dto.UserDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
        boolean saveUser = userService.saveUser(userDto);
        if (saveUser) {
            return ResponseEntity.ok("successfully added");
        }
        return ResponseEntity.badRequest().body("User already exist");
    }

    @DeleteMapping("/user/{user_id}")
    @ApiOperation(value = "删除用户", httpMethod = "DELETE")
    public ResponseEntity deleteUser(@PathVariable("user_id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询用户列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数（0开始）", required = true, dataType = "int", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, dataType = "int", paramType = "query", defaultValue = "5")
    })
    @NotNull
    public ResponseEntity listForUser(Integer page, Integer size) {
        PageResult result = userService.listForUser(PageRequest.of(page, size, Sort.Direction.DESC,"id"));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{user_phone}")
    @ApiOperation(value = "根据手机号查询", httpMethod = "GET")
    public ResponseEntity findUser(@PathVariable("user_phone") String userPhone) {
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
        return ResponseEntity.ok(userService.updateUser(userName, userId));
    }


    @GetMapping("/user_list")
    @ApiOperation(value = "动态查询用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAge", value = "用户年龄", dataType = "int", paramType = "Query"),
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String", paramType = "Query")
    })
    public ResponseEntity userList(Integer userAge, String userName) {
        return ResponseEntity.ok(userService.dynamicFind(userAge, userName));
    }
}
