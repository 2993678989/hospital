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
 * 住院信息表
 */
@Data
@ToString
public class Hospitalization implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 病历号
     */
    private Integer cases;
    /**
     * 护理人
     */
    private String caregivers;
    /**
     * 床位
     */
    private String bed;
    /**
     * 押金
     */
    private Double deposit;
    /**
     * 余额
     */
    private Double balance;
    /**
     * 病情
     */
    @TableField("`condition`")
    private String condition;
    /**
     * 入院时间
     */
    @TableField("admission_time")
    private String admissionTime;
    @TableField(exist = false)
    private Registration registration;
}
