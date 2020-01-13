package com.zhiyou100.hospital.service.impl;

import com.zhiyou100.hospital.mapper.TurnoverMapper;
import com.zhiyou100.hospital.pojo.Turnover;
import com.zhiyou100.hospital.service.ITurnoverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:WANGXIN
 * @Date:2020/1/12 15:32
 */
@Service
public class TurnoverServiceImpl implements ITurnoverService {
    @Resource
    private TurnoverMapper turnoverMapper;
    @Override
    public List<Turnover> queryByDate(String date) {
        return turnoverMapper.queryByDate(date);
    }

    @Override
    public void add(Turnover turnover) {
        turnoverMapper.insert(turnover);
    }
}
