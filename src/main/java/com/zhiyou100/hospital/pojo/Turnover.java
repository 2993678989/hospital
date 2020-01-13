package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author:WANGXIN
 * @Date:2020/1/12 15:28
 */
@Data
@ToString
public class Turnover {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer cases;
    private Double spending;
    private String addTime;
}
