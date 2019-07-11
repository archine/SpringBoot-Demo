package com.gj.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author Gjing
 **/
@Getter
public class UserDto {
    @ApiModelProperty(name = "userName", value = "用户名", required = true, dataType = "String")
    private String userName;

    @ApiModelProperty(name = "userAge", value = "年龄", required = true, dataType = "int")
    private Integer userAge;

    @ApiModelProperty(name = "userPhone", value = "手机号", required = true, dataType = "String")
    private String userPhone;
}
