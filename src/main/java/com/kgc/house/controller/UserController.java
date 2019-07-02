package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;
import com.kgc.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UsersService usersService;
    //�÷�ҳ��ѯ����
    @RequestMapping("/selectUserAll")
    @ResponseBody
    public Map<String, Object> selectAll(UserCondition userCondition) {
        PageInfo<Users> pageInfo = usersService.selectBypage(userCondition);

        Map<String, Object> map = new HashMap<>();

        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());

        return map;
    }



    //  ��¼��֤
    @RequestMapping("/backGround")

    public String backGround(String username, String password, Model model, HttpSession session){

        //����service�ķ���
        Users users = usersService.regLogin(username, password);

        if (users!=null){

            session.setAttribute("user",users);
            session.setMaxInactiveInterval(30);
            return "/admin/admin.html";
        }else {


            model.addAttribute("info","��������˺Ŵ���");
            return "/admin/login1.jsp";
        }


    }
    
}
