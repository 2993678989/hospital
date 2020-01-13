package com.zhiyou100.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyou100.hospital.pojo.Turnover;

import java.util.List;

/**
 * @Author:WANGXIN
 * @Date:2020/1/12 15:29
 */
public interface TurnoverMapper extends BaseMapper<Turnover> {
    List<Turnover> queryByDate(String date);
}
