package com.kgc.house.client.controller;


import com.kgc.house.entity.Type;
import com.kgc.house.service.HouseService;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "typeController2")
public class TypeController {


    @Autowired
    private TypeService typeService;


    @RequestMapping("/getType")
    @ResponseBody
    public  List<Type> getType(){
        List<Type> types = typeService.selectBypage();
        return types;
    }
}
