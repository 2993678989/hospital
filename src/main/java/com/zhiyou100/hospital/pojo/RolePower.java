package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:li
 * @Date:2019/11/30 17:07
 * 角色权限中间表
 */
@Data
@ToString
@TableName("role_power")
public class RolePower implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("role_id")
    private Integer roleId;
    @TableField("power_id")
    private Integer powerId;
}
