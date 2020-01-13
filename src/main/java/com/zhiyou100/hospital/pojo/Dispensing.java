package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:li
 * @Date:2019/11/30 17:07
 * 发药表
 */
@Data
@ToString
public class Dispensing implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 病历号
     */
    private Integer cases;

    /**
     * 病人
     */
    private String name;
    /**
     * 负责人
     */
    private String head;
    /**
     * 药品id
     */
    private Integer medicineId;
    /**
     * 发药数量
     */
    private Integer dispensingNumber;
    /**
     * 已发数量
     */
    private Integer already;
    /**
     * 未发数量
     */
    private Integer unissued;
    /**
     * 本次发药数量
     */
    private Integer give;
    /**
     * 发药时间
     */
    private String dispensingTime;
    @TableField(exist = false)
    private Medicine medicine;
}
