package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:li
 * @Date:2019/11/30 17:02
 * 用户表
 */
@Data
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String email;
    private String state;
    private Integer roleId;
    private String mtime;
    @TableField(exist = false)
    private Role role;

}
