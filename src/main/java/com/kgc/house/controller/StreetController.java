package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StreetController {


    @Autowired
    private StreetService streetService;


    @RequestMapping("/selectAllStreet")
    @ResponseBody
    public Map<String, Object> selectAllStreet(Integer page, Integer rows) {

        PageInfo<Street> pageInfo = streetService.selectByPage(page, rows);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());

        return map;
    }


    @RequestMapping("/getStreetByDid")
    @ResponseBody
    public Map<String, Object> getStreetByDid(Integer page, Integer rows, Integer did) {

        PageInfo<Street> pageInfo = streetService.selectByPage2(page, rows, did);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());

        return map;
    }


}
