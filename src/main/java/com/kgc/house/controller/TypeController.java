package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TypeController {


    @Autowired
    private TypeService typeService;



    //用分页查询所有
    @RequestMapping("/selectTypeAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        PageInfo<Type> pageInfo = typeService.selectBypage(page, rows);

        Map<String, Object> map = new HashMap<>();

        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());

        return map;
    }


    //添加
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type Type) {


        int temp = typeService.insertSelective(Type);


        return "{\"result\":" + temp + "}";
    }


    //修改
    @RequestMapping("/upType")
    @ResponseBody
    public String upType(Type Type) {


        int temp = typeService.upType(Type);


        return "{\"result\":" + temp + "}";
    }


    //删除
    @RequestMapping("/delType")
    @ResponseBody
    public String deleteType(Integer id) {



        int temp = typeService.deleteByPrimaryKey(id);


        return "{\"result\":" + temp + "}";
    }


    //批量删除
    @RequestMapping("/deleteMoreType")
    @ResponseBody
    public String deleteMoreType(String ids) {


        System.out.println("ids = " + ids);
        //将字符串转化为整型数组
        String[] arys = ids.split(",");


        Integer[] id = new Integer[arys.length];
        for (int i = 0; i < arys.length; i++) {
            id[i] = Integer.parseInt(arys[i]);
        }



        int temp = typeService.deleteMoreType(id);


        return "{\"result\":" + temp + "}";
    }


}
