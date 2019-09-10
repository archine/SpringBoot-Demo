package com.gjing.quartz.repository;

import com.gjing.quartz.entity.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gjing
 **/
@Repository
public interface JobRepository extends JpaRepository<QuartzJob, Integer> {
    /**
     * 通过任务名查询
     * @param jobName 任务名
     * @return job
     */
    QuartzJob findByJobName(String jobName);
}
