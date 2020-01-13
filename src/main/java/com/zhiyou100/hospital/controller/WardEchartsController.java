package com.zhiyou100.hospital.controller;

import com.zhiyou100.hospital.service.ITurnoverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author:WANGXIN
 * @Date:2020/1/12 14:58
 */
@Controller
public class WardEchartsController {

    @RequestMapping("goWardEcharts")
    public String goWardEcharts(){
        return "hospital/wardEcharts";
    }

}
