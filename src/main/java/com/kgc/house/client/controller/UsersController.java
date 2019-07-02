package com.kgc.house.client.controller;


import com.kgc.house.entity.Users;
import com.kgc.house.service.UsersService;
import com.kgc.house.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;


    //    检查用户名是否可以用
    @RequestMapping("/checkUname")
    @ResponseBody
    public String checkUname(String username){

        //调用service的方法
        int temp= usersService.checkUname(username);

        return "{\"result\":"+temp+"}";
    }




    @RequestMapping("/regUser")

    //注册
    public String regUser(String username, String password, String telephone){

        Users users = new Users();
        users.setIsadmin(0);
        users.setName(username);
        System.out.println("username = " + username);
        users.setPassword(MD5Utils.md5Encrypt(password));
        users.setTelephone(telephone);

        //调用service的方法
        int temp= usersService.regUser(users);
        if (temp>0){
            return "login";
        }

        return "error";
    }


    //  登录验证
    @RequestMapping("/regLogin")

    public String regLogin(String username,String password,Model model,HttpSession session){

        //调用service的方法
        Users users = usersService.regLogin(username, password);

        if (users!=null){

            session.setAttribute("user",users);
            session.setMaxInactiveInterval(600);
            return "redirect:selectHouse";
        }else {


            model.addAttribute("info","密码或者账号错误");
            return "login";
        }


    }
}
