package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:li
 * @Date:2019/11/30 17:07
 * 项目管理表
 */
@Data
@ToString
@TableName("service_management")
public class ServiceManagement implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 病历号
     */
    private Integer cases;
    private String name;
    /**
     * 项目id
     */
    private Integer serviceId;
    /**
     * 收费金额
     */
    private Double charge;
    /**
     * 收费日期
     */
    private String chargeTime;
    @TableField(exist = false)
    private MyService myService;
}
