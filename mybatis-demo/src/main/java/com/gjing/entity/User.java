package com.gjing.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Gjing
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", columnDefinition = "varchar(20) not null comment '用户名'")
    private String userName;

    @Column(name = "user_age", columnDefinition = "tinyint(1) not null comment '用户年龄'")
    private Integer userAge;

    @Column(name = "create_time", columnDefinition = "datetime")
    private Date createTime;
}
