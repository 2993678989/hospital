package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:li
 * @Date:2019/11/30 17:07
 * 项目表
 */
@Data
@ToString
@TableName("service")
public class MyService implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Double money;
}
