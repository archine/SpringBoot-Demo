package com.gj.web.api;

import com.gjing.Print;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gjing
 **/
@RestController
public class TestController {

    @PostMapping("/test")
    @ApiOperation(value = "测试", httpMethod = "POST")
    @Print
    public String test() {
        return "ok";
    }
}
