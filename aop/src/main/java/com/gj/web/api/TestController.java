package com.gj.web.api;

import cn.gjing.annotation.NotNull;
import com.gj.annotations.Test;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gjing
 **/
@RestController
@RequestMapping("test1")
public class TestController {

    @GetMapping("/ok")
    @ApiOperation(value = "测试1", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "id值", dataType = "int", paramType = "query")
    @Test
    @NotNull
    public String test2(Integer id) {
        return "ok";
    }

}
