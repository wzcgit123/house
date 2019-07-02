package com.kgc.house.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("houseController2")
public class HouseController {


    @Autowired
    private HouseService houseService;

    @RequestMapping("/selectNoPassHouse")
    @ResponseBody
    public Map<String,Object> selectNoPassHouse(Integer page, Integer rows){

        PageInfo<House> pageInfo = houseService.selectNoPass(page,rows);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());

        return map;

    }



    @RequestMapping("/selectPassHouse")
    @ResponseBody
    public Map<String,Object> selectPassHouse(Integer page, Integer rows){

        PageInfo<House> pageInfo = houseService.selectPass(page,rows);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());

        return map;

    }


    @RequestMapping("/passHouse")
    @ResponseBody
    public Map<String,Object> passHouse(String id){

        int temp=houseService.passHouse(id);  //o±Ì æŒ¥…Û∫À
        Map<String,Object> map=new HashMap<>();
        map.put("result",temp);
        return map;

    }

}
