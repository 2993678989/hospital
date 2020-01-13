package com.zhiyou100.hospital.util;

import com.zhiyou100.hospital.pojo.Doctor;
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
public class DoctorPoi {
    public String create(List<Doctor> doctors) {
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
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("身份证类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("身份证号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("固定电话");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("生日");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("邮箱");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("部门");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("学历");
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);
        cell.setCellValue("入职时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 12);
        cell.setCellValue("备注");
        cell.setCellStyle(style);


        String url = "D:/";
        //向单元格里填充数据
        int i = 0;
        for (Doctor doctor:doctors) {
            row = sheet.createRow(++i);
            row.createCell(0).setCellValue(doctor.getId()+"");
            row.createCell(1).setCellValue(doctor.getName()+"");
            row.createCell(2).setCellValue(doctor.getIDType()+"");
            row.createCell(3).setCellValue(doctor.getIDCard()+"");
            row.createCell(4).setCellValue(doctor.getPhone()+"");
            row.createCell(5).setCellValue(doctor.getLandline()+"");
            row.createCell(6).setCellValue(doctor.getSex()+"");
            row.createCell(7).setCellValue(doctor.getBirthday()+"");
            row.createCell(8).setCellValue(doctor.getEmail()+"");
            row.createCell(9).setCellValue(doctor.getDepartment().getName()+"");
            row.createCell(10).setCellValue(doctor.getEducation()+"");
            row.createCell(11).setCellValue(doctor.getEntryTime()+"");
            row.createCell(12).setCellValue(doctor.getRemarks()+"");
        }

        try {
            //默认导出到E盘下
            FileOutputStream out = new FileOutputStream(url + "医生表"+System.currentTimeMillis() +".xls");
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
