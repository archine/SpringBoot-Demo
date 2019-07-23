package com.gj.web.api;

import cn.gjing.result.ResultVo;
import com.gj.domain.User;
import com.gj.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResultVo saveUser(User user) {
        Integer saveUser = userService.saveUser(user);
        if (saveUser == 1) {
            return ResultVo.success();
        }
        return ResultVo.error();
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询指定用户", httpMethod = "GET")
    public ResultVo findUser(@PathVariable("id") Integer id) {
        return ResultVo.success(userService.findById(id));
    }

    @GetMapping("/user_list")
    @ApiOperation(value = "查询所有用户", httpMethod = "GET")
    public ResultVo findUserList() {
        return ResultVo.success(userService.findAll());
    }
}
