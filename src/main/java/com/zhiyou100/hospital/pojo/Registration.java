package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:li
 * @Date:2019/11/30 17:07
 * 挂号表
 */
@Data
@ToString
public class Registration implements Serializable {
    /**
     * 病历号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField("ID_type")
    private String IDType;
    @TableField("ID_card")
    private Long IDCard;
    /**
     * 社保号
     */
    @TableField("social_security")
    private Long socialSecurity;
    private Long phone;
    /**
     * 是否自费
     */
    private String self;
    private String sex;
    private Integer age;
    /**
     * 职业
     */
    private String profession;
    /**
     * 初复诊
     */
    @TableField("first_visit_again")
    private String firstVisitAgain;
    /**
     * 医生编号
     */
    @TableField("doctor_id")
    private Integer doctorId;
    @TableField("department_id")
    private Integer departmentId;
    /**
     * 挂号状态
     */
    private String state;
    /**
     * 挂号时间
     */
    private String time;
    @TableField(exist = false)
    private Doctor doctor;
    @TableField(exist = false)
    private Department department;

}
