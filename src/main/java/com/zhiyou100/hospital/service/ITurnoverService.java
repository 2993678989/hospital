package com.zhiyou100.hospital.service;

import com.zhiyou100.hospital.pojo.Turnover;

import java.util.List;

/**
 * @Author:WANGXIN
 * @Date:2020/1/12 15:29
 */
public interface ITurnoverService {
    List<Turnover> queryByDate(String date);
    void add(Turnover turnover);
}
