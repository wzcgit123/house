package com.kgc.house.client.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StreetsController {


    @Autowired
    private StreetService streetService;



    @RequestMapping("/getStreetByDid2")
    @ResponseBody
    public List<Street> getStreetByDid2(Integer did) {
        //调用业务
        return streetService.selectByPrimaryKey2(did);
    }

}