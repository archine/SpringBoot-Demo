package com.gjing.quartz.entity;

import com.gjing.quartz.enums.JobStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Gjing
 **/
@Data
@Entity
@Table(name = "quartz_job")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class QuartzJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "job_name", columnDefinition = "varchar(20) not null comment '任务名'")
    private String jobName;

    @Column(name = "job_class", columnDefinition = "varchar(50) not null comment '任务类完整信息'")
    private String jobClass;

    @Column(name = "job_description", columnDefinition = "varchar(50) comment '任务描述'")
    private String description;

    @Column(name = "job_cron", columnDefinition = "varchar(30) not null comment 'cron表达式'")
    private String cron;

    @Column(name = "job_status", columnDefinition = "tinyint(2) default 1 comment '1：运行中；2、暂停'")
    @Convert(converter = JobStatus.JobStatusConverter.class)
    private JobStatus status;

    @Column(name = "create_time", columnDefinition = "datetime")
    @CreatedDate
    private Date createTime;
}
