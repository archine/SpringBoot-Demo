package com.gj.web.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gjing
 **/
@RestController
@RequestMapping("/test2")
public class Test2Controller {

    @GetMapping("/ok")
    @ApiOperation(value = "测试2-1", httpMethod = "GET")
    public String ok() {
        return "ok";
    }

    @GetMapping("/ok2/{id}")
    @ApiOperation(value = "测试2-2", httpMethod = "GET")
    public String ok2(@PathVariable("id") String id) {
        return "ok: " + id;
    }
}
