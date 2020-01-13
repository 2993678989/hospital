package com.zhiyou100.hospital.controller;

import com.zhiyou100.hospital.pojo.Turnover;
import com.zhiyou100.hospital.pojo.TurnoverEcharts;
import com.zhiyou100.hospital.service.ITurnoverService;
import com.zhiyou100.hospital.util.MyDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:WANGXIN
 * @Date:2020/1/12 16:20
 */
@Controller
public class TurnoverEchartsController {
    @Resource
    private ITurnoverService turnoverService;

    @RequestMapping("goTurnoverEcharts")
    public String goTurnoverEcharts(){
        return "hospital/turnoverEcharts.html";
    }
    @RequestMapping("turnoverEcharts")
    @ResponseBody
    public List<TurnoverEcharts> turnoverEcharts(){
        List<TurnoverEcharts> turnoverEcharts = new ArrayList<>();
        TurnoverEcharts wardEcharts1 = new TurnoverEcharts();
        List<Turnover> dturnovers = turnoverService.queryByDate(MyDate.todayFirstDate());
        //今日的收益
        wardEcharts1.setType("今日的收益");
        int dprofit = 0;
        for (Turnover d:dturnovers
        ) {
            dprofit+=d.getSpending();
        }
        wardEcharts1.setProfit(dprofit);
        turnoverEcharts.add(wardEcharts1);
        //本月的收益
        TurnoverEcharts wardEcharts2 = new TurnoverEcharts();
        List<Turnover> mturnovers = turnoverService.queryByDate(MyDate.monthFistDate());
        wardEcharts2.setType("本月的收益");
        int mprofit = 0;
        for (Turnover m:mturnovers
        ) {
            mprofit+=m.getSpending();
        }
        wardEcharts2.setProfit(mprofit);
        turnoverEcharts.add(wardEcharts2);
        //今年的收益
        TurnoverEcharts wardEcharts3 = new TurnoverEcharts();
        List<Turnover> yturnovers = turnoverService.queryByDate(MyDate.monthFistDate());
        wardEcharts3.setType("今年的收益");
        int yprofit = 0;
        for (Turnover y:yturnovers
        ) {
            yprofit+=y.getSpending();
        }
        wardEcharts3.setProfit(yprofit);
        turnoverEcharts.add(wardEcharts3);
        return turnoverEcharts;
    }
}
