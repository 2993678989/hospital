package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:li
 * @Date:2019/11/30 17:07
 * 医生表
 */
@Data
@ToString
public class Doctor implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    /**
     * 身份证类型
     */
    @TableField("ID_type")
    private String IDType;
    /**
     * 身份证号
     */
    @TableField("ID_card")
    private Long IDCard;
    private Long phone;
    /**
     * 固定电话
     */
    private String landline;
    private String sex;
    private Date birthday;
    private String email;
    /**
     * 部门id
     */
    @TableField("department_id")
    private Integer departmentId;
    /**
     * 学历
     */
    private String education;
    /**
     * 入职时间
     */
    @TableField("entry_time")
    private String entryTime;
    /**
     * 备注
     */
    private String remarks;
    @TableField(exist = false)
    private Department department;
}
