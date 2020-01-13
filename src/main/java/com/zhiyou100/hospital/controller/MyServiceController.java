package com.zhiyou100.hospital.controller;

import com.zhiyou100.hospital.pojo.MyService;
import com.zhiyou100.hospital.service.IMyServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2020/1/7 10:11
 */
@Controller
public class MyServiceController {
    @Resource
    private IMyServiceService myServiceService;

    @ResponseBody
    @RequestMapping("myServicesAjax")
    public MyService myServicesAjax(String name){
        MyService byName = myServiceService.findByName(name);
        System.out.println(byName);
        return byName;
    }

}
