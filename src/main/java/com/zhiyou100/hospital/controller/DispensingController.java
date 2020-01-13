package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Dispensing;
import com.zhiyou100.hospital.pojo.Doctor;
import com.zhiyou100.hospital.pojo.Medicine;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.service.IDispensingService;
import com.zhiyou100.hospital.service.IMedicineService;
import com.zhiyou100.hospital.service.IRegistrationService;
import com.zhiyou100.hospital.util.MyDate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author:li
 * @Date:2020/1/7 21:40
 * 发药表
 */
@Controller
public class DispensingController {
    @Resource
    private IDispensingService dispensingService;
    @Resource
    private IRegistrationService registrationService;
    @Resource
    private IMedicineService medicineService;

    @RequestMapping("dispensings")
    @RequiresPermissions("在院发药")
    public String dispensings(Model model, Integer current, String cases){
        System.out.println(current+cases);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<Dispensing> wrapper = new QueryWrapper();
        if (cases != null && !"".equals(cases.trim())) {
            model.addAttribute("cases", cases);
            int id1 = Integer.parseInt(cases);
            wrapper.eq("cases", id1);
        }
        Page<Dispensing> page = new Page<>(current, 2);
        IPage<Dispensing> dispensingIPage = dispensingService.queryPage(page, wrapper);
        model.addAttribute("dispensings", dispensingIPage.getRecords());
        model.addAttribute("pages", dispensingIPage.getPages());
        model.addAttribute("current", dispensingIPage.getCurrent());
        model.addAttribute("total", dispensingIPage.getTotal());
        System.out.println(dispensingIPage.getRecords());
        return "hospital/dispensing";
    }

    @RequestMapping("addDispensing")
    @ResponseBody
    public Integer addDispensing(Dispensing dispensing){
        Registration byCases = registrationService.findByCases(dispensing.getCases());
        if ("已住院".equals(byCases.getState())){
            byCases.setState("未结算");
            registrationService.update(byCases);
        }
        dispensingService.add(dispensing);
        return 200;
    }

    @RequiresPermissions("在院发药")
    @RequestMapping("dispensingByCases")
    public String dispensingByCases(Model model,Integer current,Integer cases){
        if (current==null){
            current=1;
        }
        System.out.println(current+":"+cases);
        Page<Dispensing> page = new Page<>(current, 10);
        QueryWrapper<Dispensing> wrapper = new QueryWrapper();
        IPage<Dispensing> dispensingIPage = dispensingService.queryPageByCases(page, wrapper, cases);
        List<Dispensing> dispensings = dispensingIPage.getRecords();
                System.out.println(dispensings);
        model.addAttribute("dispensings",dispensings);
        return "hospital/dispensing-look";
    }

    @RequestMapping("updateDispensingsByIds")
    @ResponseBody
    public Integer updateDispensingsByIds(Integer[] ids){
        for (Integer id:ids
             ) {
            System.out.println(id);
            QueryWrapper<Dispensing> wrapper = new QueryWrapper();
            wrapper.eq("cases",id);
            List<Dispensing> dispensings = dispensingService.queryByWrapper(wrapper);
            for (Dispensing dispensing:dispensings
                 ) {
                System.out.println("未发药数量==============>"+dispensing.getUnissued());
                if (dispensing.getUnissued()!=0){
                    Medicine medicine1 = medicineService.queryById(dispensing.getMedicineId());
                    medicine1.setSurplus(medicine1.getSurplus()-dispensing.getUnissued());
                    medicineService.update(medicine1);
                    dispensing.setUnissued(0);
                    dispensing.setAlready(dispensing.getDispensingNumber());
                    dispensing.setId(null);
                    dispensing.setDispensingTime(MyDate.date(new Date()));
                    dispensing.setGive(dispensing.getDispensingNumber()-dispensing.getUnissued());
                    dispensingService.dispenAll(dispensing);

                }
            }
        }
        return 200;
    }
    @RequiresPermissions("在院发药")
    @RequestMapping("goDispensingDive")
    public String goDispensingDive(Integer cases,Model model){
        QueryWrapper<Dispensing> wrapper = new QueryWrapper();
        wrapper.eq("cases",cases);
        List<Dispensing> dispensings = dispensingService.queryByWrapper(wrapper);
        Dispensing[] dispensings1 = dispensings.toArray(new Dispensing[0]);
        model.addAttribute("cases",dispensings1[0].getCases());
        model.addAttribute("name",dispensings1[0].getName());
        model.addAttribute("dispensings",dispensings);
        return "hospital/dispensing-give";
    }
    @RequestMapping("giveDispensing")
    @ResponseBody
    public Integer giveDispensing(Integer give,Integer cases,Integer medicine){
        System.out.println(give+":"+cases+":"+medicine);
        QueryWrapper<Dispensing> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cases",cases);
        queryWrapper.eq("medicine_id",medicine);
        List<Dispensing> dispensings = dispensingService.queryByWrapper(queryWrapper);
        for (Dispensing dispensing:dispensings
             ) {
            Medicine medicine1 = medicineService.queryById(medicine);
            medicine1.setSurplus(medicine1.getSurplus()-give);
            medicineService.update(medicine1);
            dispensing.setAlready(dispensing.getAlready()+give);
            dispensing.setUnissued(dispensing.getUnissued()+give);
            dispensing.setDispensingTime(MyDate.date(new Date()));
            dispensingService.add(dispensing);
        }
        return 200;
    }

}
