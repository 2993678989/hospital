package com.zhiyou100.hospital.util;

import com.zhiyou100.hospital.pojo.Hospitalization;
import com.zhiyou100.hospital.pojo.Registration;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/4 15:50
 */
@Component
public class HospitalizationPoi {
    public String create(List<Hospitalization> hospitalizations) {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("导出");
        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short)15);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        //创建第一行（也可以称为表头）
        HSSFRow row = sheet.createRow(0);
        //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("id");
        cell.setCellStyle(style);
        cell = row.createCell( (short) 1);
        cell.setCellValue("病历号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("护理人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("床位");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("押金");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("病情");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("入院时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("电话");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("医生");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("科室");
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);
        cell.setCellValue("状态");
        cell.setCellStyle(style);

//        //添加一些数据，这里先写死，大家可以换成自己的集合数据
//        List<Registration> list = new ArrayList<>();
//        list.add(new Registration());
//        list.add(new Registration());
//        list.add(new Registration());

        String url = "D:/";
        //向单元格里填充数据
        int i = 0;
        for (Hospitalization hospitalization:hospitalizations) {
            row = sheet.createRow(++i);
            row.createCell(0).setCellValue(hospitalization.getId()+"");
            row.createCell(1).setCellValue(hospitalization.getCases()+"");
            row.createCell(2).setCellValue(hospitalization.getCaregivers()+"");
            row.createCell(3).setCellValue(hospitalization.getBed()+"");
            row.createCell(4).setCellValue(hospitalization.getDeposit()+"");
            row.createCell(5).setCellValue(hospitalization.getCondition()+"");
            row.createCell(6).setCellValue(hospitalization.getAdmissionTime()+"");
            row.createCell(7).setCellValue(hospitalization.getRegistration().getName()+"");
            row.createCell(8).setCellValue(hospitalization.getRegistration().getPhone()+"");
            row.createCell(9).setCellValue(hospitalization.getRegistration().getDoctor().getName()+"");
            row.createCell(10).setCellValue(hospitalization.getRegistration().getDepartment().getName()+"");
            row.createCell(11).setCellValue(hospitalization.getRegistration().getState()+"");
        }

        try {
            //默认导出到E盘下
            FileOutputStream out = new FileOutputStream(url + "住院表"+System.currentTimeMillis() +".xls");
            wb.write(out);
            out.close();
            return "导出成功";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "导出失败";
        } catch (IOException e) {
            e.printStackTrace();
            return "导出失败";
        }
    }
}
