package com.gjing.quartz.service;

import cn.gjing.util.BeanUtil;
import com.gjing.quartz.entity.QuartzJob;
import com.gjing.quartz.entity.dto.JobDTO;
import com.gjing.quartz.enums.JobStatus;
import com.gjing.quartz.repository.JobRepository;
import com.gjing.quartz.util.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gjing
 **/
@Service
public class JobService {
    @Resource
    private JobRepository jobRepository;
    @Resource
    private Scheduler scheduler;

    /**
     * 添加任务
     *
     * @param jobDTO 任务DTO
     */
    public void saveJob(JobDTO jobDTO) {
        QuartzJob job = jobRepository.findByJobName(jobDTO.getJobName());
        if (job != null) {
            throw new IllegalArgumentException("该任务已存在");
        }
        QuartzJob quartzJob = BeanUtil.copyProperties(jobDTO, QuartzJob.class);
        quartzJob.setStatus(JobStatus.RUNNING);
        QuartzUtils.addJob(scheduler, quartzJob);
        jobRepository.save(quartzJob);
    }

    /**
     * 操作任务
     *
     * @param jobId 任务id
     */
    public void updateJob(Integer jobId, JobStatus jobStatus) {
        QuartzJob quartzJob = jobRepository.findById(jobId).orElseThrow(() -> new NullPointerException("任务不存在"));
        quartzJob.setStatus(jobStatus);
        jobRepository.save(quartzJob);
        if (jobStatus == JobStatus.RUNNING) {
            QuartzUtils.resumeJob(scheduler, quartzJob.getJobName());
        } else {
            QuartzUtils.pauseJob(scheduler, quartzJob.getJobName());
        }
    }

    /**
     * 查询所有任务
     *
     * @return 任务列表
     */
    public List<QuartzJob> listJob() {
        return jobRepository.findAll();
    }

    /**
     * 删除任务
     *
     * @param id 任务id
     */
    public void deleteJob(Integer id) {
        QuartzJob quartzJob = jobRepository.findById(id).orElseThrow(() -> new NullPointerException("任务不存在"));
        QuartzUtils.deleteJob(scheduler, quartzJob.getJobName());
        jobRepository.delete(quartzJob);
    }
}
