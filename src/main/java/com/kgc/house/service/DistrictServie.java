package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;

import java.util.List;

public interface DistrictServie {


   PageInfo<District> selectBypage(Integer page,Integer pageSize);


    int insertSelective(District record);

    int upDistrict(District district);


    int deleteByPrimaryKey(Integer id);


    int deleteMoreDistrict(Integer[] ids);

    List<District> selectBypage2();
}
