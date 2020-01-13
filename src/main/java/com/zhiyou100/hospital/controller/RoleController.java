package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Power;
import com.zhiyou100.hospital.pojo.Role;
import com.zhiyou100.hospital.pojo.RolePower;
import com.zhiyou100.hospital.service.IPowerService;
import com.zhiyou100.hospital.service.IRolePowerService;
import com.zhiyou100.hospital.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:WANGXIN
 * @Date:2020/1/11 14:42
 */
@Controller
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IPowerService powerService;
    @Resource
    private IRolePowerService rolePowerService;
    @RequiresPermissions("角色管理")
    @RequestMapping("roles")
    public String roles(Model model, Integer current, String name){
        System.out.println(current+name);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<Role> wrapper = new QueryWrapper();
        if (name != null && !"".equals(name.trim())) {
            name = name.trim();
            model.addAttribute("name", name);
            wrapper.like("r.name", name);
        }
        wrapper.groupBy("r.name");
        Page<Role> page = new Page<>(current, 2);
        IPage<Role> rolePage = roleService.queryPage(page, wrapper);
//        System.out.println(rPage.getPages()); //总共页数
//        System.out.println(rPage.getCurrent());//当前页数
//        System.out.println(rPage.getRecords());//数据
//        System.out.println(rPage.getSize());//一页有几个
//        System.out.println(rPage.getTotal());//一共多少条数据
        model.addAttribute("roles", rolePage.getRecords());
        model.addAttribute("pages", rolePage.getPages());
        model.addAttribute("current", rolePage.getCurrent());
        model.addAttribute("total", rolePage.getTotal());
        System.out.println(rolePage.getRecords());
        return "Role/index";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("role")
    public String role(Integer id,Model model){
        Role role = roleService.queryById(id);
        model.addAttribute("role",role);
        List<Power> powers = powerService.queryAll();
        model.addAttribute("powers",powers);
        return "Role/editRole";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("updateRole")
    public String roleAjax(Integer id,Integer[] powerIds){
        QueryWrapper<RolePower> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        rolePowerService.deleteByWrapper(queryWrapper);
        if (powerIds!=null){
            RolePower rolePower = new RolePower();
            rolePower.setRoleId(id);
            for (Integer powerId:powerIds
            ) {
                rolePower.setPowerId(powerId);
                rolePowerService.add(rolePower);
            }
        }
        return "redirect:roles";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("deleteRole")
    public String deleteRole(Integer id){
        roleService.delete(id);
        return "redirect:roles";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("deleteRoles")
    @ResponseBody
    public int deleteRoles(Integer[] ids){
        int num = 0;
        if (ids!=null){
            for (Integer id: ids
                 ) {
                roleService.delete(id);
                num++;
            }
        }
        return num;
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("goAddRole")
    public String goAddRole(Model model){
        List<Power> powers = powerService.queryAll();
        model.addAttribute("powers",powers);
        return "Role/addRole";
    }

    @RequestMapping("roleAjax")
    @ResponseBody
    public Integer roleAjax(String name){
        Role byWrapper = roleService.findByName(name);
        if (byWrapper!=null){
            return 1;
        }
        return 0;
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("addRole")
    public String addRole(String name,Integer[] powerIds){
        Role role = new Role();
        role.setName(name);
        roleService.add(role);
        Role byName = roleService.findByName(name);
        if (powerIds!=null){
            RolePower rolePower = new RolePower();
            rolePower.setRoleId(byName.getId());
            for (Integer powerId:powerIds
                 ) {
                rolePower.setPowerId(powerId);
                rolePowerService.add(rolePower);
            }
        }
        return "redirect:roles";
    }
}
