package com.zhiyou100.hospital.service;

import com.zhiyou100.hospital.pojo.MyService;

/**
 * @Author:li
 * @Date:2019/12/1 20:33
 */
public interface IMyServiceService extends BaseService<MyService> {
    MyService findByName(String name);
}
