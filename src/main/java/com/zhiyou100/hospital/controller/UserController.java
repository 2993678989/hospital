package com.zhiyou100.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Role;
import com.zhiyou100.hospital.pojo.User;
import com.zhiyou100.hospital.service.IRoleService;
import com.zhiyou100.hospital.service.IUserService;
import com.zhiyou100.hospital.util.MyDate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 14:58
 */
@Controller
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;

    @RequestMapping("go")
    public String goLogin(){
        return "login";
    }
    @RequestMapping("login")
    public String login(String username, String password, HttpServletRequest request, Model model){

//        Integer login = userService.login(username.trim(), password.trim());
//        if (login==0){
//            model.addAttribute("login","账号不存在");
//            return "login";
//        }
//        if (login==1) {
//            model.addAttribute("login", "密码错误");
//            return "login";
//        }
//        if (login==2){
//            //密码正确
//            request.getSession().setAttribute("myUser",userService.queryAll(username,password));
//            return "index";
//        }
        System.out.println(username+":"+password);
        User userByName = userService.findByName(username);
        if (userByName==null){
            model.addAttribute("login","账号不存在");
            return "login";
        }else if("禁用".equals(userByName.getState())){
            model.addAttribute("login","账号以禁用");
            return "login";
        } else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,password,true);
            try{
                subject.login(token);
                System.out.println("登陆通过");
            }catch (UnknownAccountException e){
                model.addAttribute("login","账号不存在");
            return "login";
            }catch (IncorrectCredentialsException e){
                model.addAttribute("login", "密码错误");
            return "login";
            }
            request.getSession().setAttribute("myUser",userService.queryAll(username,password));
            return "index";
        }
    }
    @ResponseBody
    @RequestMapping("menu")
    public Object menu(HttpServletRequest request, HttpServletResponse response){
        Object myUser = request.getSession().getAttribute("myUser");
        if (myUser==null){
            try {
                response.sendRedirect("/go");
            } catch (IOException e) {
                System.out.println("重定向失败");
            }
        }
        return myUser;
    }
    @RequestMapping("out")
    public String out(HttpServletRequest request){
        request.getSession().removeAttribute("myUser");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("已退出登陆");
        return "login";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("users")
    public String users(Model model, Integer current, String account){
        System.out.println(current+account);
        if (current == null) {
            current = 1;
        }
        QueryWrapper<User> wrapper = new QueryWrapper();
        if (account != null && !"".equals(account.trim())) {
            account = account.trim();
            model.addAttribute("account", account);
            wrapper.like("u.account", account);
        }
        Page<User> page = new Page<>(current, 2);
        IPage<User> byLike = userService.findByLike(page, wrapper);
//        System.out.println(rPage.getPages()); //总共页数
//        System.out.println(rPage.getCurrent());//当前页数
//        System.out.println(rPage.getRecords());//数据
//        System.out.println(rPage.getSize());//一页有几个
//        System.out.println(rPage.getTotal());//一共多少条数据
        model.addAttribute("users", byLike.getRecords());
        model.addAttribute("pages", byLike.getPages());
        model.addAttribute("current", byLike.getCurrent());
        model.addAttribute("total", byLike.getTotal());
        return "User/index";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("user")
    public String user(Integer id,Model model){
        User byId = userService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles",roles);
        model.addAttribute("user",byId);
        return "User/editUser";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("updateUser")
    public String updateUser(User user){
        userService.update(user);
        return "redirect:users";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("deleteUser")
    public String deleteUser(Integer id){
        userService.delete(id);
        return "redirect:users";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("goAddUser")
    public String goAddUser(Model model){
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles",roles);
        return "User/addUser";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("addUser")
    public String addUser(User user){
        user.setMtime(MyDate.date(new Date()));
        System.out.println(user);
        userService.add(user);
        return "redirect:users";
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("deleteUsers")
    @ResponseBody
    public int deleteUsers(Integer[] ids){
        int num =0;
        for (Integer id: ids
             ) {
            System.out.println(id);
            userService.delete(id);
            num++;
        }
        return num;
    }
    @RequiresPermissions("角色管理")
    @RequestMapping("findUserByAccount")
    @ResponseBody
    public int findUserByAccount(String account){
        User byName = userService.findByName(account);
        if (byName!=null){
            return 1;
        }
        return 0;
    }







}
