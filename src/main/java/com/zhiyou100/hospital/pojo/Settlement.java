package com.zhiyou100.hospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/11/30 17:07
 * 结算消费
 */
@Data
@ToString
public class Settlement implements Serializable {
    /**
     * 收费项目
     */
    private List<ServiceManagement> serviceManagements;
    /**
     * 收费药品
     */
    private List<Dispensing> dispensingList;
    /**
     * 总花费
     */
    private Double expenditure;
    /**
     * 押金
     */
    private Double deposit;
    /**
     * 余额
     */
    private Double balance;
}
