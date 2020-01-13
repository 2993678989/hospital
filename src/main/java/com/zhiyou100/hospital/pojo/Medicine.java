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
 * 药品表
 */
@Data
@ToString
public class Medicine implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String number;
    private String url;
    /**
     * 进价
     */
    private Double purchasePrice;
    /**
     * 售价
     */
    private Double sellingPrice;
    private String name;
    /**
     * 药品类型
     */
    private String type;
    /**
     * 简单描述
     */
    @TableField("`describe`")
    private String describe;
    /**
     * 生产日期
     */
    private Date production;
    /**
     * 过期日期
     */
    private Date overdueTime;
    /**
     * 保质期
     */
    private String shelfLife;
    /**
     * 详细描述
     */
    private String detailedDescription;
    /**
     * 生产厂商
     */
    private String manufacturer;
    /**
     * 服用说明
     */
    @TableField("`explain`")
    private String explain;
    /**
     * 进货量
     */
    private Integer quantityPurchase;
    /**
     * 剩余
     */
    private Integer surplus;
    /**
     * 出售状态
     */
    private String state;
    /**
     * 备注
     */
    private String remarks;
}
