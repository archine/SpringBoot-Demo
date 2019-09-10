package com.gjing.quartz.enums;

import lombok.Getter;

import javax.persistence.AttributeConverter;

/**
 * @author Gjing
 **/
@Getter
public enum JobStatus {
    RUNNING(1), PAUSE(2);
    private Integer type;

    JobStatus(Integer type) {
        this.type = type;
    }

    public static JobStatus of(Integer type) {
        for (JobStatus jobStatus : JobStatus.values()) {
            if (jobStatus.type == type) {
                return jobStatus;
            }
        }
        throw new NullPointerException("未找到你的类型！");
    }

    /**
     * 枚举转换器
     */
    public static class JobStatusConverter implements AttributeConverter<JobStatus, Integer> {
        @Override
        public Integer convertToDatabaseColumn(JobStatus jobStatus) {
            return jobStatus.type;
        }

        @Override
        public JobStatus convertToEntityAttribute(Integer type) {
            return of(type);
        }
    }

}
