package com.zhiyou100.hospital.controller;

import com.zhiyou100.hospital.pojo.Medicine;
import com.zhiyou100.hospital.pojo.MedicineEcharts;
import com.zhiyou100.hospital.service.IMedicineEchartsService;
import com.zhiyou100.hospital.service.IMedicineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:WANGXIN
 * @Date:2020/1/12 14:14
 */
@Controller
public class MedicineEchartsController {

    @Resource
    private IMedicineEchartsService medicineEchartsService;


    @RequestMapping("goMedicineEcharts")
    public String goMedicineEcharts(){
        return "hospital/medicineEcharts";
    }

    @RequestMapping("medicineEcharts")
    @ResponseBody
    public List<MedicineEcharts> medicineEcharts(){
        return medicineEchartsService.queryAll();
    }


}
