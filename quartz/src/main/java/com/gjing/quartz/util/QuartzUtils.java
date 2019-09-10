package com.gjing.quartz.util;

import com.gjing.quartz.entity.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * @author Gjing
 **/
@Slf4j
public class QuartzUtils {
    /**
     * 创建定时任务 定时任务创建之后默认启动状态
     *
     * @param scheduler  调度器
     * @param jobBean 定时任务信息类
     */
    @SuppressWarnings("unchecked")
    public static void addJob(Scheduler scheduler, QuartzJob jobBean) {
        //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
        Class<? extends Job> jobClass = null;
        try {
            jobClass = (Class<? extends Job>) Class.forName(jobBean.getJobClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 构建定时任务信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobBean.getJobName()).build();
        // 设置定时任务执行方式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobBean.getCron());
        // 构建触发器trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobBean.getJobName()).withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("创建定时任务出错：{}", jobBean.getJobName());
            e.printStackTrace();
        }
    }

    /**
     * 根据任务名称暂停定时任务
     *
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void pauseJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("暂停定时任务出错: {}", jobName);
            e.printStackTrace();
        }
    }

    /**
     * 根据任务名称恢复定时任务
     *
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void resumeJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("恢复定时任务出错: {}", jobName);
            e.printStackTrace();
        }
    }

    /**
     * 根据任务名称立即运行一次定时任务
     *
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void runOnce(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("运行一次定时任务出错: {}", jobName);
            e.printStackTrace();
        }
    }

    /**
     * 更新定时任务
     *
     * @param scheduler  调度器
     * @param jobBean 定时任务信息类
     */
    public static void updateJob(Scheduler scheduler, QuartzJob jobBean) {
        try {
            //获取到对应任务的触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(jobBean.getJobName());
            //设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobBean.getCron());
            //重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务出错: {}", jobBean.getJobName());
            e.printStackTrace();

        }
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     *
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void deleteJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("删除定时任务出错: {}", jobName);
            e.printStackTrace();
        }
    }

    /**
     * 获取某个任务的状态
     * @param scheduler 调度器
     * @param jobName 任务名
     * @return 状态
     */
    public static String getState(Scheduler scheduler, String jobName) {
        TriggerKey triggerKey = new TriggerKey(jobName);
        String name = null;
        try {
            name = scheduler.getTriggerState(triggerKey).name();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return name;
    }
}
