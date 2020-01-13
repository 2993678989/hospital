package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Medicine;
import com.zhiyou100.hospital.service.IMedicineService;
import com.zhiyou100.hospital.util.MedicinePoi;
import com.zhiyou100.hospital.util.MyDate;
import com.zhiyou100.hospital.util.Picture;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/6 15:00
 */
@Controller
public class MedicineController {
    @Resource
    private IMedicineService medicineService;
    @Resource
    private MedicinePoi medicinePoi;

    @RequestMapping("medicines")
    @RequiresPermissions("药品管理")
    public String medicines(Model model, Integer current, String name, String type) {
        System.out.println(current+name+type);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<Medicine> wrapper = new QueryWrapper();
        if (name != null && !"".equals(name.trim())) {
            name = name.trim();
            model.addAttribute("name", name);
            wrapper.like("name", name);
        }
        if (type != null && !"".equals(type.trim())) {
            type = type.trim();
            model.addAttribute("type", type);
            wrapper.like("type", type);
        }
        Page<Medicine> page = new Page<>(current, 2);
        IPage<Medicine> rPage = medicineService.queryPage(page, wrapper);
//        System.out.println(rPage.getPages()); //总共页数
//        System.out.println(rPage.getCurrent());//当前页数
//        System.out.println(rPage.getRecords());//数据
//        System.out.println(rPage.getSize());//一页有几个
//        System.out.println(rPage.getTotal());//一共多少条数据
        model.addAttribute("medicines", rPage.getRecords());
        model.addAttribute("pages", rPage.getPages());
        model.addAttribute("current", rPage.getCurrent());
        model.addAttribute("total", rPage.getTotal());
        return "medicine/index";
    }

    /**
     * 根据id查询单条数据,展示在详情页
     */
    @RequestMapping("medicine")
    @RequiresPermissions("药品管理")
    public String medicine(Model model, Integer id) {
        model.addAttribute("medicine", medicineService.queryById(id));
        return "medicine/look";
    }

    /**
     * 前往添加页面,添加数据
     */
    @RequestMapping("goAddMedicine")
    @RequiresPermissions("药品管理")
    public String goAddMedicine(Model model) {
        model.addAttribute("number",System.currentTimeMillis());
        return "medicine/add";
    }

    /**
     * 添加信息
     */
    @RequestMapping("addMedicine")
    @RequiresPermissions("药品管理")
    public String addMedicine(Medicine medicine, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String medicine1 = Picture.picture(file, request);
        medicine.setUrl(medicine1);
        medicine.setSurplus(medicine.getQuantityPurchase());
        medicine.setState("销售中");
        Date date = new Date(medicine.getProduction().getTime());
        Date date1 = MyDate.dateAddDays(date, Integer.parseInt(medicine.getShelfLife()));
        long time = date1.getTime();
        java.sql.Date sqlDate = new java.sql.Date(time);
        medicine.setOverdueTime(sqlDate);
        System.out.println(medicine);
        medicineService.add(medicine);
        return "redirect:medicines";
    }

    /**
     * 使用id查询这条数据的全部信息
     * 并且查询所有科室
     * 用于修改单条数据
     */
    @RequestMapping("upMedicine")
    @RequiresPermissions("药品管理")
    public String upMedicine(Model model, Integer id) {
        Medicine medicine = medicineService.queryById(id);
        model.addAttribute("medicine", medicine);
        return "medicine/edit";
    }

    /**
     * 根据修改后的数据修改数据库
     */
    @RequestMapping("updateMedicine")
    @RequiresPermissions("药品管理")
    public String updateMedicine(Medicine medicine,@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()){
            String medicine1 = Picture.picture(file, request);
            medicine.setUrl(medicine1);
        }
        Date date = new Date(medicine.getProduction().getTime());
        Date date1 = MyDate.dateAddDays(date, Integer.parseInt(medicine.getShelfLife()));
        long time = date1.getTime();
        java.sql.Date sqlDate = new java.sql.Date(time);
        medicine.setOverdueTime(sqlDate);
        System.out.println(medicine);
        medicineService.update(medicine);
        return "redirect:medicines";
    }

    @ResponseBody
    @RequestMapping("medicinePoi")
    @RequiresPermissions("药品管理")
    public String registrationPoi(String[] ids) {
        List<Medicine> medicines = new ArrayList<>();
        for (String id:ids
        ) {
            medicines.add(medicineService.queryById(Integer.valueOf(id)));
        }
        medicinePoi.create(medicines);
        return "200";
    }
    @ResponseBody
    @RequestMapping("medicinesAjax")
    public List<Medicine> medicinesAjax(){
        List<Medicine> medicines = medicineService.queryAll();
        System.out.println(medicines);
        return medicines;
    }
}
