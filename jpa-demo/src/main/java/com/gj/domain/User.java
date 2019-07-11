package com.gj.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Gjing
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", columnDefinition = "varchar(10) not null comment '用户名'")
    private String userName;

    @Column(name = "user_age", columnDefinition = "int not null comment '年龄'")
    private Integer userAge;

    @Column(name = "user_phone", columnDefinition = "varchar(11) not null comment '手机号'")
    private String userPhone;

    @Column(name = "create_time", columnDefinition = "datetime")
    @CreatedDate
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "datetime")
    @LastModifiedDate
    private Date updateTime;

}
