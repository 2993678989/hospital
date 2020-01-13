package com.zhiyou100.hospital.util;

import com.zhiyou100.hospital.pojo.Medicine;
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
public class MedicinePoi {
    public String create(List<Medicine> medicines) {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("导出");
        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short)20);
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("图片地址");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("进价");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("售价");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("药品名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("药品类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("简单描述");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("生产日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("过期日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("保质期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);
        cell.setCellValue("详细描述");
        cell.setCellStyle(style);
        cell = row.createCell((short) 12);
        cell.setCellValue("生产厂商");
        cell.setCellStyle(style);
        cell = row.createCell((short) 13);
        cell.setCellValue("服用说明");
        cell.setCellStyle(style);
        cell = row.createCell((short) 14);
        cell.setCellValue("进货量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 15);
        cell.setCellValue("剩余");
        cell.setCellStyle(style);
        cell = row.createCell((short) 16);
        cell.setCellValue("出售状态");
        cell.setCellStyle(style);
        cell = row.createCell((short) 17);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        String url = "D:/";
        //向单元格里填充数据
        int i = 0;
        for (Medicine medicine:medicines) {
            row = sheet.createRow(++i);
            row.createCell(0).setCellValue(medicine.getId()+"");
            row.createCell(1).setCellValue(medicine.getNumber()+"");
            row.createCell(2).setCellValue(medicine.getUrl()+"");
            row.createCell(3).setCellValue(medicine.getPurchasePrice()+"");
            row.createCell(4).setCellValue(medicine.getSellingPrice()+"");
            row.createCell(5).setCellValue(medicine.getName()+"");
            row.createCell(6).setCellValue(medicine.getType()+"");
            row.createCell(7).setCellValue(medicine.getDescribe()+"");
            row.createCell(8).setCellValue(medicine.getProduction()+"");
            row.createCell(9).setCellValue(medicine.getOverdueTime()+"");
            row.createCell(10).setCellValue(medicine.getShelfLife()+"");
            row.createCell(11).setCellValue(medicine.getDetailedDescription()+"");
            row.createCell(12).setCellValue(medicine.getManufacturer()+"");
            row.createCell(13).setCellValue(medicine.getExplain()+"");
            row.createCell(14).setCellValue(medicine.getQuantityPurchase()+"");
            row.createCell(15).setCellValue(medicine.getSurplus()+"");
            row.createCell(16).setCellValue(medicine.getState()+"");
            row.createCell(17).setCellValue(medicine.getRemarks()+"");
        }

        try {
            //默认导出到E盘下
            FileOutputStream out = new FileOutputStream(url + "药品表"+System.currentTimeMillis() +".xls");
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
