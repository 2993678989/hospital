package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.*;
import com.zhiyou100.hospital.service.*;
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
 * @Author:WANGXIN
 * @Date:2020/1/9 15:08
 * 出院结算表
 * 出院表使用的还是住院表的实体类
 */
@Controller
public class SettlementController {


    @Resource
    private IHospitalizationService hospitalizationService;
    @Resource
    private IServiceManagementService serviceManagementService;
    @Resource
    private IDispensingService dispensingService;
    @Resource
    private IRegistrationService registrationService;
    @Resource
    private ITurnoverService turnoverService;

    @RequiresPermissions("住院结算")
    @RequestMapping("settlements")
    public String hospitalizations(Model model, Integer current, String cases, String name){
        System.out.println("住院表查找条件===>"+current+cases+name);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<Hospitalization> wrapper = new QueryWrapper();
        if (cases != null && !"".equals(cases.trim())) {
            model.addAttribute("cases", cases);
            int id1 = Integer.parseInt(cases);
            wrapper.like("cases", id1);
        }
        if (name != null && !"".equals(name.trim())) {
            model.addAttribute("name", name);
            wrapper.like("r.name", name);
        }
        wrapper.eq("r.state","未结算");
        Page<Hospitalization> page = new Page<>(current, 2);
        IPage<Hospitalization> rPage = hospitalizationService.queryPage(page, wrapper);
        model.addAttribute("hospitalizations", rPage.getRecords());
        model.addAttribute("pages", rPage.getPages());
        model.addAttribute("current", rPage.getCurrent());
        model.addAttribute("total", rPage.getTotal());
        System.out.println(rPage.getRecords()+"|"+rPage.getPages()+"|"+rPage.getCurrent()+"|"+rPage.getTotal());
        return "hospital/account";
    }
    @RequiresPermissions("住院结算")
    @RequestMapping("goAccountLook")
    public String goAccountLook(Integer cases,Model model){
        Settlement settlement = new Settlement();
        //总花费
        Double expenditure = 0.0;
        //押金
        Double deposit = 0.0;
        //余额
        Double balance = 0.0;
        //查询住院登记表
        QueryWrapper<Hospitalization> hospitalizationQueryWrapper = new QueryWrapper();
        hospitalizationQueryWrapper.eq("cases",cases);
        Hospitalization hospitalization = hospitalizationService.queryByWrapper(hospitalizationQueryWrapper);
        deposit=hospitalization.getDeposit();
        settlement.setDeposit(deposit);
        //查询全部发药信息,用于返还给页面展示
        QueryWrapper<Dispensing> dispensingQueryWrapper = new QueryWrapper();
        dispensingQueryWrapper.eq("cases",cases);
        dispensingQueryWrapper.ne("give",0);
        List<Dispensing> dispensings = dispensingService.queryByCases(dispensingQueryWrapper);
        //查询发药情况,用于计算消费金额
        List<Dispensing> dispensings1 = dispensingService.queryByWrapper(dispensingQueryWrapper);
        for (Dispensing d:dispensings1
        ) {
            expenditure += d.getAlready()*d.getMedicine().getSellingPrice();
        }
        //查询全部收费项目信息,英语返还给页面展示
        QueryWrapper<ServiceManagement> serviceManagementQueryWrapper = new QueryWrapper();
        serviceManagementQueryWrapper.eq("s.cases",cases);
        List<ServiceManagement> serviceManagements = serviceManagementService.queryByCases(serviceManagementQueryWrapper);
        for (ServiceManagement s:serviceManagements
        ) {
            expenditure+=s.getCharge();
        }
        balance=deposit-expenditure;
        settlement.setBalance(balance);
        settlement.setExpenditure(expenditure);
        settlement.setDispensingList(dispensings);
        settlement.setServiceManagements(serviceManagements);
        System.out.println(settlement);
        model.addAttribute("settlement",settlement);
        return "hospital/account-look";
    }
    /**
     * 结算
     */
    @RequestMapping("settle")
    @ResponseBody
    @RequiresPermissions("住院结算")
    public Settlement settle(Integer cases){
        Settlement settlement = new Settlement();
        //总花费
        Double expenditure = 0.0;
        //押金
        Double deposit = 0.0;
        //余额
        Double balance = 0.0;
        //查询住院登记表
        QueryWrapper<Hospitalization> hospitalizationQueryWrapper = new QueryWrapper();
        hospitalizationQueryWrapper.eq("cases",cases);
        Hospitalization hospitalization = hospitalizationService.queryByWrapper(hospitalizationQueryWrapper);
        deposit=hospitalization.getDeposit();
        //查询全部发药信息,用于返还给页面展示
        QueryWrapper<Dispensing> dispensingQueryWrapper = new QueryWrapper();
        dispensingQueryWrapper.eq("cases",cases);
        dispensingQueryWrapper.ne("give",0);
//        List<Dispensing> dispensings = dispensingService.queryByCases(dispensingQueryWrapper);
        //查询发药情况,用于计算消费金额
        List<Dispensing> dispensings1 = dispensingService.queryByWrapper(dispensingQueryWrapper);
        for (Dispensing d:dispensings1
        ) {
            expenditure += d.getAlready()*d.getMedicine().getSellingPrice();
        }
        //查询全部收费项目信息,英语返还给页面展示
        QueryWrapper<ServiceManagement> serviceManagementQueryWrapper = new QueryWrapper();
        serviceManagementQueryWrapper.eq("s.cases",cases);
        List<ServiceManagement> serviceManagements = serviceManagementService.queryByCases(serviceManagementQueryWrapper);
        for (ServiceManagement s:serviceManagements
        ) {
            expenditure+=s.getCharge();
        }
        balance=deposit-expenditure;
        settlement.setBalance(balance);
        settlement.setExpenditure(expenditure);
        settlement.setDeposit(deposit);
        System.out.println(settlement);
        return settlement;
    }
    @RequestMapping("onSettle")
    @ResponseBody
    @RequiresPermissions("住院结算")
    public Integer onSettle(Integer cases){
        Turnover turnover = new Turnover();
        //总花费
        Double expenditure = 0.0;
        //押金
        Double deposit = 0.0;
        //余额
        Double balance = 0.0;
        //查询住院登记表
        QueryWrapper<Hospitalization> hospitalizationQueryWrapper = new QueryWrapper();
        hospitalizationQueryWrapper.eq("cases",cases);
        Hospitalization hospitalization = hospitalizationService.queryByWrapper(hospitalizationQueryWrapper);
        deposit=hospitalization.getDeposit();
        //查询全部发药信息,用于返还给页面展示
        QueryWrapper<Dispensing> dispensingQueryWrapper = new QueryWrapper();
        dispensingQueryWrapper.eq("cases",cases);
        dispensingQueryWrapper.ne("give",0);
//        List<Dispensing> dispensings = dispensingService.queryByCases(dispensingQueryWrapper);
        //查询发药情况,用于计算消费金额
        List<Dispensing> dispensings1 = dispensingService.queryByWrapper(dispensingQueryWrapper);
        for (Dispensing d:dispensings1
        ) {
            expenditure += d.getAlready()*d.getMedicine().getSellingPrice();
        }
        //查询全部收费项目信息,英语返还给页面展示
        QueryWrapper<ServiceManagement> serviceManagementQueryWrapper = new QueryWrapper();
        serviceManagementQueryWrapper.eq("s.cases",cases);
        List<ServiceManagement> serviceManagements = serviceManagementService.queryByCases(serviceManagementQueryWrapper);
        for (ServiceManagement s:serviceManagements
        ) {
            expenditure+=s.getCharge();
        }
        balance=deposit-expenditure;
        turnover.setCases(cases);
        turnover.setSpending(expenditure);
        turnover.setAddTime(MyDate.date(new Date()));
        turnoverService.add(turnover);
        Registration byCases = registrationService.findByCases(cases);
        byCases.setState("已结算");
        registrationService.update(byCases);
        return 200;
    }


}
