package com.gjing.controller;

import cn.gjing.annotation.NotNull;
import com.gjing.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Gjing
 **/
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user")
    @ApiOperation(value = "添加用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String", required = true, paramType = "Query"),
            @ApiImplicitParam(name = "userAge", value = "年龄", dataType = "int", required = true, paramType = "Query")
    })
    @NotNull
    public ResponseEntity saveUser(String userName, Integer userAge) {
        return ResponseEntity.ok(userService.saveUser(userName, userAge));
    }

    @GetMapping("/user_list")
    @ApiOperation(value = "查询用户列表", httpMethod = "GET")
    public ResponseEntity listUser() {
        return ResponseEntity.ok(userService.listUser());
    }
}
