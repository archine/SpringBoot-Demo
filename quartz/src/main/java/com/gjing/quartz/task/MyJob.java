package com.gjing.quartz.task;

import cn.gjing.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author Gjing
 **/
@Slf4j
public class MyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("当前线程：{}，任务开始执行，当前时间：{}", Thread.currentThread().getName(), TimeUtil.dateToString(new Date()));
    }
}
