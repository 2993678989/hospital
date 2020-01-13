package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Hospitalization;
import com.zhiyou100.hospital.pojo.MyService;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.pojo.ServiceManagement;
import com.zhiyou100.hospital.service.IMyServiceService;
import com.zhiyou100.hospital.service.IRegistrationService;
import com.zhiyou100.hospital.service.IServiceManagementService;
import com.zhiyou100.hospital.util.MyDate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author:li
 * @Date:2020/1/7 10:11
 */
@Controller
public class ServiceManagementController {
    @Resource
    private IServiceManagementService serviceManagementService;
    @Resource
    private IMyServiceService myServiceService;
    @Resource
    private IRegistrationService registrationService;

    /**
     * 分页查询收费项目管理表
     */
    @RequiresPermissions("收费项目登记")
    @RequestMapping("serviceManagements")
    public String serviceManagements(Model model, Integer current, String cases, String name){
        System.out.println("收费项目查找条件===>"+current+cases+name);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<ServiceManagement> wrapper = new QueryWrapper();
        if (cases != null && !"".equals(cases.trim())) {
            model.addAttribute("cases", cases);
            int id1 = Integer.parseInt(cases);
            wrapper.like("s.cases", id1);
        }
        if (name != null && !"".equals(name.trim())) {
            model.addAttribute("name", name);
            wrapper.like("s.name", name);
        }
        Page<ServiceManagement> page = new Page<>(current, 2);
        IPage<ServiceManagement> rPage = serviceManagementService.queryPage(page, wrapper);
        model.addAttribute("serviceManagements", rPage.getRecords());
        model.addAttribute("pages", rPage.getPages());
        model.addAttribute("current", rPage.getCurrent());
        model.addAttribute("total", rPage.getTotal());
        System.out.println(rPage.getRecords()+"|"+rPage.getPages()+"|"+rPage.getCurrent()+"|"+rPage.getTotal());
        return "hospital/charge";
    }
    /**
     * 前往添加收费项目管理信息页面,分为两种情况
     * 一种是直接点击添加按钮
     * 一种是点击插连接
     */
    @RequiresPermissions("收费项目登记")
    @RequestMapping("goAddServiceManagement")
    public String goAddServiceManagement(Model model,String cases,String name){
        if (cases==null||"".equals(cases)){
            model.addAttribute("cases","");
        }else {
            model.addAttribute("cases",cases);
        }
        if (name==null||"".equals(name)){
            model.addAttribute("name","");
        }else {
            model.addAttribute("name",name);
        }
        List<MyService> myServices = myServiceService.queryAll();
        model.addAttribute("myServices",myServices);
        return "hospital/charge-new";
    }
    /**
     * 添加收费项目管理信息
     */
    @RequestMapping("addServiceManagement")
    @ResponseBody
    public Integer addServiceManagement(ServiceManagement serviceManagement){
        Registration byCases = registrationService.findByCases(serviceManagement.getCases());
        if ("已住院".equals(byCases.getState())){
            byCases.setState("未结算");
            registrationService.update(byCases);
        }
        serviceManagement.setChargeTime(MyDate.date(new Date()));
        System.out.println(serviceManagement);
        serviceManagementService.add(serviceManagement);

        return 200;
    }

}
