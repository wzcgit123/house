package com.kgc.house.client.controller;

import com.kgc.house.sms.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author ������
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
        //������֤��:
        int code=(int)(Math.random()*10000);
        //ʹ��session����
        session.setAttribute("code",code);
        session.setMaxInactiveInterval(120);  //2����

        //�������͵��ֻ���Ϣ
        String msg="���:��ĵ�����֤����:"+code+",�벻Ҫ�����˻�ȡ";

        //������Ϣ
        int result=SmsUtil.sendMsm(tel,msg);
        return "{\"result\":"+result+"}";
    }

}
