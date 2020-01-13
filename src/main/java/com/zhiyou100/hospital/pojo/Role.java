package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/11/30 17:05
 * 角色表
 */
@Data
@ToString
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(exist = false)
    private String powerName;
    @TableField(exist = false)
    private List<Power> powers;
}
