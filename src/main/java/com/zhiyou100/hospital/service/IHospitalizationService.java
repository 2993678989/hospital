package com.zhiyou100.hospital.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhiyou100.hospital.pojo.Hospitalization;

/**
 * @Author:li
 * @Date:2019/12/1 20:32
 */
public interface IHospitalizationService extends BaseService<Hospitalization> {
    Hospitalization queryByWrapper(QueryWrapper<Hospitalization> wrapper);
}
