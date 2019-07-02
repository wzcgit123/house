package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.service.DistrictServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller

public class DistrictController {

    @Autowired
    private DistrictServie districtServie;



    //用分页查询所有
    @RequestMapping("/selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        PageInfo<District> pageInfo = districtServie.selectBypage(page, rows);

        Map<String, Object> map = new HashMap<>();

        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());

        return map;
    }


    //添加
    @RequestMapping("/addDistrict")
    @ResponseBody
    public String addDistrict(District district) {


        int temp = districtServie.insertSelective(district);


        return "{\"result\":" + temp + "}";
    }


    //修改
    @RequestMapping("/upDistrict")
    @ResponseBody
    public String upDistrict(District district) {


        int temp = districtServie.upDistrict(district);


        return "{\"result\":" + temp + "}";
    }


    //删除
    @RequestMapping("/delDistrict")
    @ResponseBody
    public String deleteDistrict(Integer id) {



        int temp = districtServie.deleteByPrimaryKey(id);


        return "{\"result\":" + temp + "}";
    }


    //批量删除
    @RequestMapping("/deleteMoreDistrict")
    @ResponseBody
    public String deleteMoreDistrict(String ids) {


        System.out.println("ids = " + ids);
        //将字符串转化为整型数组
        String[] arys = ids.split(",");


        Integer[] id = new Integer[arys.length];
        for (int i = 0; i < arys.length; i++) {
            id[i] = Integer.parseInt(arys[i]);
        }



        int temp = districtServie.deleteMoreDistrict(id);


        return "{\"result\":" + temp + "}";
    }
}