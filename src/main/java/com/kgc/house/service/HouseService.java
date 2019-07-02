package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;

import java.util.List;

public interface HouseService {
    public int add(House house);

    //查询用户的出租房
    public PageInfo<House> getUserHouseByPage(Integer page,Integer rows,Integer uid);

    House selectById(String id);

    int updateHouse(House house);

    PageInfo<House> selectNoPass(Integer page,Integer rows);

    int deleteHouse(String id);


    PageInfo<House> selectPass(Integer page,Integer rows);

    int passHouse(String id);

    PageInfo<House> getHouseByBrowser(HouseCondition condition);
}
