package com.zhiyou100.hospital.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @Author:li
 * @Date:2019/10/10 16:40
 */
@Component
public class Picture {
    public static String picture(MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()){
            HttpSession session = request.getSession();
            String fileName = "";
            fileName+=System.currentTimeMillis();
            fileName+=session.getId();
            fileName+=file.getOriginalFilename();
            String path = "E:/test";
            File dest = new File(path + "/" + fileName);
            if(!dest.getParentFile().exists()){
                //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest);
                System.out.println(fileName);
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
