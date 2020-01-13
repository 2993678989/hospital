package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Hospitalization;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.service.IDepartmentService;
import com.zhiyou100.hospital.service.IHospitalizationService;
import com.zhiyou100.hospital.service.IRegistrationService;
import com.zhiyou100.hospital.util.HospitalizationPoi;
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
 * @Date:2020/1/6 14:34
 */
@Controller
public class HospitalizationController {
    @Resource
    private IHospitalizationService hospitalizationService;
    @Resource
    private HospitalizationPoi hospitalizationPoi;
    @Resource
    private IRegistrationService registrationService;

    @RequestMapping("hospitalizations")
    @RequiresPermissions("住院办理")
    public String hospitalizations(Model model, Integer current, String cases, String doctor, String department, String strTime, String endTime){
        System.out.println("住院表查找条件===>"+current+cases+department+doctor+strTime+endTime);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<Hospitalization> wrapper = new QueryWrapper();
        if (cases != null && !"".equals(cases.trim())) {
            model.addAttribute("cases", cases);
            int id1 = Integer.parseInt(cases);
            wrapper.like("cases", id1);
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
            wrapper.gt("h.admission_time",strTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            String[] split = endTime.split("/");
            if (split.length>2){
                endTime=split[2]+"-"+split[0]+"-"+split[1];
            }
            model.addAttribute("endTime", endTime);
            wrapper.le("h.admission_time", endTime);
        }
        Page<Hospitalization> page = new Page<>(current, 2);
        IPage<Hospitalization> rPage = hospitalizationService.queryPage(page, wrapper);
//        System.out.println(rPage.getPages()); //总共页数
//        System.out.println(rPage.getCurrent());//当前页数
//        System.out.println(rPage.getRecords());//数据
//        System.out.println(rPage.getSize());//一页有几个
//        System.out.println(rPage.getTotal());//一共多少条数据
        model.addAttribute("hospitalizations", rPage.getRecords());
        model.addAttribute("pages", rPage.getPages());
        model.addAttribute("current", rPage.getCurrent());
        model.addAttribute("total", rPage.getTotal());
        System.out.println(rPage.getRecords()+"|"+rPage.getPages()+"|"+rPage.getCurrent()+"|"+rPage.getTotal());
        return "hospital/index";
    }
    /**
     * 根据id查询单条数据,展示在详情页
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("hospitalization")
    public String hospitalization(Model model, Integer id) {
        Hospitalization hospitalization = hospitalizationService.queryById(id);
        System.out.println(hospitalization);
        model.addAttribute("hospitalization", hospitalization);
        return "hospital/look";
    }
    /**
     * 前往录入住院页面,添加数据
     * 需要使用到科室信息
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("goAddHospitalization")
    public String goAddHospitalization() {
        return "hospital/add";
    }
    /**
     * 入院时间为当前时间使用工具类MyDate格式化时间
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("addHospitalization")
    public String addHospitalization(Hospitalization hospitalization) {
        Registration registration = registrationService.queryById(hospitalization.getCases());
        registration.setState("已住院");
        registrationService.update(registration);
        hospitalization.setAdmissionTime(MyDate.date(new Date(System.currentTimeMillis())));
        hospitalization.setBalance(hospitalization.getDeposit());
        System.out.println(hospitalization);
        hospitalizationService.add(hospitalization);
        return "redirect:hospitalizations";
    }
    /**
     * 使用id查询这条数据的全部入院信息
     * 用于修改单条入院数据
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("upHospitalization")
    public String upHospitalization(Model model, Integer id) {
        Hospitalization hospitalization = hospitalizationService.queryById(id);
        model.addAttribute("hospitalization", hospitalization);
        return "hospital/edit";
    }
    /**
     * 根据修改后的入院表的数据修改数据库
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("updateHospitalization")
    public String updateHospitalization(Hospitalization hospitalization) {
        hospitalizationService.update(hospitalization);
        return "redirect:hospitalizations";
    }
    @ResponseBody
    @RequiresPermissions("住院办理")
    @RequestMapping("hospitalizationPoi")
    public String hospitalizationPoi(String[] ids) {
        List<Hospitalization> hospitalization = new ArrayList<>();
        for (String id:ids
        ) {
            hospitalization.add(hospitalizationService.queryById(Integer.valueOf(id)));
        }
        hospitalizationPoi.create(hospitalization);
        return "200";
    }
    /**
     * 出院办理
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("outHospitalization")
    public String outHospitalization(Integer cases){
        System.out.println("给病历号为"+cases+"的病人办理出院");
        Registration byCases = registrationService.findByCases(cases);
        if ("已结算".equals(byCases.getState())){
            byCases.setState("已出院");
            registrationService.update(byCases);
            System.out.println("已出院");
        }else {
            System.out.println("当前状态不可出院");
        }
        return "redirect:hospitalizations";
    }
    /**
     * 批量出院办理
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("outHospitalizations")
    @ResponseBody
    public int outHospitalizations(Integer[] ids){
        int num=0;
        for (Integer id :
                ids) {
            Hospitalization hospitalization = hospitalizationService.queryById(id);
            Integer cases = hospitalization.getCases();
            System.out.println("给病历号为"+cases+"的病人办理出院");
            Registration byCases = registrationService.findByCases(cases);
            if ("已结算".equals(byCases.getState())){
                byCases.setState("已出院");
                registrationService.update(byCases);
                System.out.println("成功已出院");
                num++;
            }else {
                System.out.println("当前状态不可出院");
            }
        }
        return num;
    }
    /**
     * 退院办理
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("retreatHospitalization")
    public String retreatHospitalization(Integer cases){
        System.out.println("给病历号为"+cases+"的病人办理退院");
        Registration byCases = registrationService.findByCases(cases);
        if("已住院".equals(byCases.getState())){
            java.util.Date date = MyDate.strToDate(byCases.getTime());
            java.util.Date date1 = MyDate.dateAddHours(date, 1);
            if (MyDate.dateCompare(date1,new java.util.Date())!=-1){
                byCases.setState("已退院");
                System.out.println();
                registrationService.update(byCases);
                System.out.println("退院成功");
            }else {
                System.out.println("可退院时间已过");
            }
        }else {
            System.out.println("当前状态不可退院");
        }
        return "redirect:hospitalizations";
    }
    /**
     * 批量退院办理
     */
    @RequiresPermissions("住院办理")
    @RequestMapping("retreatHospitalizations")
    @ResponseBody
    public int retreatHospitalizations(Integer[] ids){
        int num=0;
        for (Integer id:ids
             ) {
            Hospitalization hospitalization = hospitalizationService.queryById(id);
            Integer cases = hospitalization.getCases();
            System.out.println("给病历号为"+cases+"的病人办理退院");
            Registration byCases = registrationService.findByCases(cases);
            System.out.println(byCases);
            if("已住院".equals(byCases.getState())){
                java.util.Date date = MyDate.strToDate(byCases.getTime());
                java.util.Date date1 = MyDate.dateAddHours(date, 1);
                if (MyDate.dateCompare(date1,new java.util.Date())!=-1){
                    byCases.setState("已退院");
                    System.out.println();
                    registrationService.update(byCases);
                    System.out.println("退院成功");
                    num++;
                }else {
                    System.out.println("可退院时间已过");
                }
            }else {
                System.out.println("当前状态不可退院");
            }
        }
        return num;
    }
}
