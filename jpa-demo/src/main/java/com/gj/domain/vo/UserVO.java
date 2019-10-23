package com.gj.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Gjing
 **/
@ToString
@Getter
@Setter
@ApiModel("用户DTO")
@AllArgsConstructor
public class UserVO {
    @ApiModelProperty(name = "id",value = "用户id")
    private Long id;

    @ApiModelProperty(name = "userName",value = "用户名")
    private String userName;

    @ApiModelProperty(name = "userAge",value = "年龄")
    private Integer userAge;

    @ApiModelProperty(name = "userPhone", value = "手机号")
    private String userPhone;
}
