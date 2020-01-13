package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Doctor;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.service.IDepartmentService;
import com.zhiyou100.hospital.service.IDoctorService;
import com.zhiyou100.hospital.service.IRegistrationService;
import com.zhiyou100.hospital.util.MyDate;
import com.zhiyou100.hospital.util.RegistrationPoi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 17:02
 * 挂号表数据的操作
 */
@Controller
public class RegistrationController {
    @Resource
    private IRegistrationService registrationService;
    @Resource
    private IDepartmentService departmentService;
    @Resource
    private IDoctorService doctorService;
    @Resource
    private RegistrationPoi registrationPoi;

    /**
     * 进入挂号表主页面查询全部
     * 分页查询每页两条数据,
     * registration包含了查询条件,strTime为起始时间,endTime为结束时间,都可以没有
     */
    @RequestMapping("registrations")
    @RequiresPermissions("挂号信息管理")
    public String registrations(Model model, Integer current, String id, String doctor, String department, String strTime, String endTime) {
        System.out.println("挂号表查找条件===>"+current+id+department+doctor+strTime+endTime);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<Registration> wrapper = new QueryWrapper();
        if (id != null && !"".equals(id.trim())) {
            model.addAttribute("id", id);
            int id1 = Integer.parseInt(id);
            wrapper.eq("r.id", id1);
        }
        if (doctor != null && !"".equals(doctor.trim())) {
            model.addAttribute("doctor", doctor);
            wrapper.like("d.name", doctor);
        }
        if (department != null && !"".equals(department.trim())) {
            department = department.trim();
            model.addAttribute("department", department);
            wrapper.like("dt.name", department);
        }
        if (strTime != null && !"".equals(strTime)) {
            String[] split = strTime.split("/");
            if (split.length>2){
                strTime=split[2]+"-"+split[0]+"-"+split[1];
            }
            model.addAttribute("strTime", strTime);
            wrapper.gt("time",strTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            String[] split = endTime.split("/");
            if (split.length>2){
                endTime=split[2]+"-"+split[0]+"-"+split[1];
            }
            model.addAttribute("endTime", endTime);
            wrapper.le("time", endTime);
        }
        Page<Registration> page = new Page<>(current, 2);
        IPage<Registration> rPage = registrationService.queryPage(page, wrapper);
//        System.out.println(rPage.getPages()); //总共页数
//        System.out.println(rPage.getCurrent());//当前页数
//        System.out.println(rPage.getRecords());//数据
//        System.out.println(rPage.getSize());//一页有几个
//        System.out.println(rPage.getTotal());//一共多少条数据
        model.addAttribute("registrations", rPage.getRecords());
        model.addAttribute("pages", rPage.getPages());
        model.addAttribute("current", rPage.getCurrent());
        model.addAttribute("total", rPage.getTotal());
        return "registration/index";
    }

    /**
     * 根据id查询单条数据,展示在详情页
     */
    @RequiresPermissions("挂号信息管理")
    @RequestMapping("registration")
    public String registration(Model model, Integer id) {
        model.addAttribute("registration", registrationService.queryById(id));
        return "registration/look";
    }
    /**
     * 在添加入院信息时使用
     */
    @RequestMapping("registrationAjax")
    @ResponseBody
    public Registration registrationAjax(Integer id) {
        QueryWrapper<Registration> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("state","已挂号");
        Registration registration1 = registrationService.queryByWrapper(queryWrapper);
        System.out.println(registration1);
        return registration1;
    }
    /**
     * 在添加收费项目时使用
     */
    @RequestMapping("zregistrationAjax")
    @ResponseBody
    public Registration zregistrationAjax(Integer id) {
        QueryWrapper<Registration> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("state","已住院");
        Registration registration1 = registrationService.queryByWrapper(queryWrapper);
        System.out.println(registration1);
        return registration1;
    }

    /**
     * 前往挂号页面,添加挂号数据
     * 需要使用到科室信息
     */
    @RequiresPermissions("挂号信息管理")
    @RequestMapping("goAddRegistration")
    public String goAddRegistration(Model model) {
        model.addAttribute("departments", departmentService.selectList());
        return "registration/add";
    }

    /**
     * 添加挂号信息,自动添加为已挂号,
     * 挂号时间为当前时间使用工具类MyDate格式化时间
     */
    @RequiresPermissions("挂号信息管理")
    @RequestMapping("addRegistration")
    public String addRegistration(Registration registration) {
        registration.setState("已挂号");
        registration.setTime(MyDate.date(new Date(System.currentTimeMillis())));
        System.out.println(registration);
        registrationService.add(registration);
        return "redirect:registrations";
    }

    /**
     * 使用id查询这条数据的全部挂号信息
     * 并且查询所有科室
     * 根据所在科室查询科室所有医生
     * 用于修改单条挂号数据
     */
    @RequiresPermissions("挂号信息管理")
    @RequestMapping("upRegistration")
    public String upRegistration(Model model, Integer id) {
        Registration registration = registrationService.queryById(id);
        model.addAttribute("registration", registration);
        model.addAttribute("departments", departmentService.selectList());
        model.addAttribute("doctors", doctorService.selectByDepartmentId(registration.getDepartment().getId()));
        return "registration/edit";
    }

    /**
     * 根据修改后的挂号表的数据修改数据库
     */
    @RequiresPermissions("挂号信息管理")
    @RequestMapping("updateRegistration")
    public String updateRegistration(Registration registration) {
        registrationService.update(registration);
        return "redirect:registrations";
    }


    /**
     * 单条数据退号
     */
    @RequiresPermissions("挂号信息管理")
    @RequestMapping("updateState")
    public String updateState(Registration registration) {
        Registration registration1 = registrationService.queryById(registration.getId());
        String time = registration1.getTime();
        java.util.Date date = MyDate.strToDate(time);
        java.util.Date date1 = MyDate.dateAddHours(date, 1);
        if (MyDate.dateCompare(date1,new java.util.Date())!=-1){
            registration.setState("已退号");
            registrationService.update(registration);
        }
        return "redirect:registrations";
    }

    /**
     * 使用ajax的多条数据退号
     */
    @ResponseBody
    @RequiresPermissions("挂号信息管理")
    @RequestMapping("updateStates")
    public String updateStates(String[] ids) {
        Registration registration = new Registration();
        for (String id :
                ids) {
            Integer id1 = Integer.valueOf(id);
            Registration registration1 = registrationService.queryById(id1);
            String time = registration1.getTime();
            java.util.Date date = MyDate.strToDate(time);
            java.util.Date date1 = MyDate.dateAddHours(date, 1);
            if (MyDate.dateCompare(date1,new java.util.Date())!=-1){
                registration.setState("已退号");
                registrationService.update(registration);
            }
        }
        return "200";
    }
    @ResponseBody
    @RequiresPermissions("挂号信息管理")
    @RequestMapping("registrationPoi")
    public String registrationPoi(String[] ids) {
        List<Registration> registrations = new ArrayList<>();
        for (String id:ids
             ) {
            registrations.add(registrationService.queryById(Integer.valueOf(id)));
        }
        registrationPoi.create(registrations);
        return "200";
    }

}
