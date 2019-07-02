package com.kgc.house.client.controller;


import com.kgc.house.entity.District;
import com.kgc.house.service.DistrictServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "districtController2")
public class DistrictController {


    @Autowired
    private DistrictServie districtServie;


    @RequestMapping("/getDistrict")
    @ResponseBody
    private List<District> getDistrict(){
        List<District> districts = districtServie.selectBypage2();
        return districts;
    }

}
