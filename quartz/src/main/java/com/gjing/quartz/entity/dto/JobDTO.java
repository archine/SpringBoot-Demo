package com.gjing.quartz.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gjing
 **/
@ApiModel("任务传输对象")
@Getter
@Setter
public class JobDTO {
    @ApiModelProperty(name = "jobName", value = "任务名", required = true, dataType = "String")
    private String jobName;

    @ApiModelProperty(name = "jobClass", value = "任务完整类名", required = true, dataType = "String")
    private String jobClass;

    @ApiModelProperty(name = "description", value = "任务描述", required = true, dataType = "String")
    private String description;

    @ApiModelProperty(name = "cron", value = "cron表达式", required = true, dataType = "String")
    private String cron;

}
