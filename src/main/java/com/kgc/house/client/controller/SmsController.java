package com.kgc.house.client.controller;

import com.kgc.house.sms.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 王建兵
 * @Classname SmsController
 * @Description TODO
 * @Date 2019/7/2 17:13
 * @Created by Administrator
 */
@Controller
@RequestMapping()
public class SmsController {

    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(String tel,HttpSession session){
        //创建验证码:
        int code=(int)(Math.random()*10000);
        //使用session保存
        session.setAttribute("code",code);
        session.setMaxInactiveInterval(120);  //2分钟

        //创建发送的手机消息
        String msg="你好:你的登入验证码是:"+code+",请不要被别人获取";

        //发送消息
        int result=SmsUtil.sendMsm(tel,msg);
        return "{\"result\":"+result+"}";
    }

}
