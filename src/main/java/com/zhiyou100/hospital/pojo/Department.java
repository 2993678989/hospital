package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:li
 * @Date:2019/11/30 17:07
 * 科室表
 */
@Data
@ToString
public class Department implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
}
