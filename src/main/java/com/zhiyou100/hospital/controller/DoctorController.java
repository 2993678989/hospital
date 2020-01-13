package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Doctor;
import com.zhiyou100.hospital.service.IDepartmentService;
import com.zhiyou100.hospital.service.IDoctorService;
import com.zhiyou100.hospital.util.DoctorPoi;
import com.zhiyou100.hospital.util.MyDate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @Date:2019/12/1 21:48
 */
@Controller
public class DoctorController {
    @Resource
    private IDoctorService doctorService;
    @Resource
    private IDepartmentService departmentService;
    @Resource
    private DoctorPoi doctorPoi;

    @RequestMapping("doctors")
    @RequiresPermissions("门诊医生管理")
    public String doctors(Model model, Integer current, String id, String name, String department) {
        System.out.println(current+id+name+department);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<Doctor> wrapper = new QueryWrapper();
        if (id != null && !"".equals(id.trim())) {
            model.addAttribute("id", id);
            int id1 = Integer.parseInt(id);
            wrapper.eq("d.id", id1);
        }
        if (name != null && !"".equals(name.trim())) {
            name = name.trim();
            model.addAttribute("name", name);
            wrapper.like("d.name", name);
        }
        if (department != null && !"".equals(department.trim())) {
            department = department.trim();
            model.addAttribute("department", department);
            wrapper.like("dt.name", department);
        }
        Page<Doctor> page = new Page<>(current, 2);
        IPage<Doctor> rPage = doctorService.queryPage(page, wrapper);
//        System.out.println(rPage.getPages()); //总共页数
//        System.out.println(rPage.getCurrent());//当前页数
//        System.out.println(rPage.getRecords());//数据
//        System.out.println(rPage.getSize());//一页有几个
//        System.out.println(rPage.getTotal());//一共多少条数据
        model.addAttribute("doctors", rPage.getRecords());
        model.addAttribute("pages", rPage.getPages());
        model.addAttribute("current", rPage.getCurrent());
        model.addAttribute("total", rPage.getTotal());
        return "doctor/index";
    }

    /**
     * 根据id查询单条数据,展示在详情页
     */
    @RequestMapping("doctor")
    @RequiresPermissions("门诊医生管理")
    public String doctor(Model model, Integer id) {
        model.addAttribute("doctor", doctorService.queryById(id));
        return "doctor/look";
    }

    /**
     * 前往添加页面,添加数据
     * 需要使用到科室信息
     */
    @RequestMapping("goAddDoctor")
    @RequiresPermissions("门诊医生管理")
    public String goAddDoctor(Model model) {
        model.addAttribute("departments", departmentService.selectList());
        return "doctor/add";
    }

    /**
     * 添加信息
     * 入职时间为当前时间使用工具类MyDate格式化时间
     */
    @RequestMapping("addDoctor")
    @RequiresPermissions("门诊医生管理")
    public String addDoctor(Doctor doctor) {
        doctor.setEntryTime(MyDate.date(new Date(System.currentTimeMillis())));
        System.out.println(doctor);
        doctorService.add(doctor);
        return "redirect:doctors";
    }

    /**
     * 使用id查询这条数据的全部信息
     * 并且查询所有科室
     * 用于修改单条数据
     */
    @RequestMapping("upDoctor")
    @RequiresPermissions("门诊医生管理")
    public String upDoctor(Model model, Integer id) {
        Doctor doctor = doctorService.queryById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departmentService.selectList());
        return "doctor/edit";
    }

    /**
     * 根据修改后的数据修改数据库
     */
    @RequestMapping("updateDoctor")
    @RequiresPermissions("门诊医生管理")
    public String updateDoctor(Doctor doctor) {
        doctorService.update(doctor);
        return "redirect:doctors";
    }

    @ResponseBody
    @RequestMapping("doctorPoi")
    @RequiresPermissions("门诊医生管理")
    public String registrationPoi(String[] ids) {
        List<Doctor> doctors = new ArrayList<>();
        for (String id:ids
        ) {
            doctors.add(doctorService.queryById(Integer.valueOf(id)));
        }
        doctorPoi.create(doctors);
        return "200";
    }


    /**
     * 使用ajax根据科室id查询其所有医生直接返还给页面
     */
    @ResponseBody
    @RequestMapping("doctorsByDepartmentId")
    public List<Doctor> doctors(Integer id) {
        return doctorService.selectByDepartmentId(id);
    }

}
