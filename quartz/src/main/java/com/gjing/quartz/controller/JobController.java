package com.gjing.quartz.controller;

import cn.gjing.annotation.NotNull;
import com.gjing.quartz.entity.dto.JobDTO;
import com.gjing.quartz.enums.JobStatus;
import com.gjing.quartz.service.JobService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Gjing
 **/
@RestController
public class JobController {
    @Resource
    private JobService jobService;

    @PostMapping("/job")
    @ApiOperation(value = "添加一个任务")
    @NotNull
    public ResponseEntity saveJob(JobDTO jobDTO) {
        jobService.saveJob(jobDTO);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/job_list")
    @ApiOperation(value = "查询任务列表")
    public ResponseEntity listJob() {
        return ResponseEntity.ok(jobService.listJob());
    }

    @PutMapping("/job_status")
    @ApiOperation(value = "暂停或者恢复任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobId", value = "任务id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "操作类型", dataType = "JobStatus", paramType = "query")
    })
    @NotNull
    public ResponseEntity updateJobStatus(Integer jobId, JobStatus type) {
        jobService.updateJob(jobId, type);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/job/{id}")
    @ApiOperation(value = "删除任务")
    public ResponseEntity deleteJob(@PathVariable("id") Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("删除成功");
    }
}
