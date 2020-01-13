package com.zhiyou100.hospital;

import com.zhiyou100.hospital.mapper.*;
import com.zhiyou100.hospital.pojo.User;
import com.zhiyou100.hospital.util.MyDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalApplicationTests {
//    @Resource
//    private DepartmentMapper departmentMapper;
//    @Resource
//    private DispensingMapper dispensingMapper;
//    @Resource
//    private DoctorMapper doctorMapper;
//    @Resource
//    private HospitalizationMapper hospitalizationMapper;
//    @Resource
//    private MedicineMapper medicineMapper;
//    @Resource
//    private PowerMapper powerMapper;
//    @Resource
//    private RegistrationMapper registrationMapper;
//    @Resource
//    private RoleMapper roleMapper;
//    @Resource
//    private RolePowerMapper rolePowerMapper;
//    @Resource
//    private ServiceManagementMapper serviceManagementMapper;
//    @Resource
//    private ServiceMapper serviceMapper;
//    @Resource
//    private SettlementMapper settlementMapper;
//    @Resource
//    private UserMapper userMapper;
    @Test
    public void contextLoads() {
//        String str = "2019-12-18 16:51:19.0";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        try {
//            date = dateFormat.parse(str);
//        } catch (ParseException e) {
//            System.out.println("转换失败");
//        }
//        System.out.println(date);
//        User user = new User();
//        System.out.println(user);
//        user.setId(1);
//        System.out.println(user);
//        String a = "";
//        System.out.println(a.hashCode());
//        a+=1;
//        System.out.println(a.hashCode());

//        System.out.println(departmentMapper.selectList(null));
//        System.out.println(dispensingMapper.selectList(null));
//        System.out.println(doctorMapper.selectList(null));
//        System.out.println(hospitalizationMapper.selectList(null));
//        System.out.println(medicineMapper.selectList(null));
//        System.out.println(powerMapper.selectList(null));
//        System.out.println(registrationMapper.selectList(null));
//        System.out.println(roleMapper.selectList(null));
//        System.out.println(rolePowerMapper.selectList(null));
//        System.out.println(serviceManagementMapper.selectList(null));
//        System.out.println(serviceMapper.selectList(null));
//        System.out.println(settlementMapper.selectList(null));
//        System.out.println(userMapper.selectList(null));
//        Date startDate = new Date();
//        int days = 30;
//            Calendar c = Calendar.getInstance();
//            c.setTime(startDate);
//            c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
//        Date time = c.getTime();
//        String date = MyDate.date(time);
//        System.out.println(date);
    }


}
