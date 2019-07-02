package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;
import com.kgc.house.entity.HouseExample;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {


    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int add(House house) {
        return houseMapper.insertSelective(house);
    }


    @Override
    public PageInfo<House> getUserHouseByPage(Integer page, Integer rows, Integer uid) {
        PageHelper.startPage(page,rows);
        //调用dao层查询出租房
        List<House> list=houseMapper.selectHouseByUserId(uid);
        //创建pageInfo
        return new PageInfo<House>(list);
    }


    @Override
    public House selectById(String id) {

        House house=houseMapper.getHouseById(id);
        return house;
    }


    @Override
    public int updateHouse(House house) {
        int i = houseMapper.updateByPrimaryKeySelective(house);
        return i;
    }


    @Override
    public PageInfo<House> selectNoPass(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);

        HouseExample example = new HouseExample();
        HouseExample.Criteria criteria = example.createCriteria();
        criteria.andIspassEqualTo(new Integer(0));

        List<House> list = houseMapper.selectByExample(example);

        PageInfo<House> pageInfo = new PageInfo<>(list);
        System.out.println(list.size());
        return pageInfo;
    }


    @Override
    public int deleteHouse(String id) {
        House house = new House();
        house.setIsdel(new Integer(1));
        house.setId(id);
        int i = houseMapper.updateByPrimaryKeySelective(house);
        return i;
    }


    @Override
    public PageInfo<House> selectPass(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);

        HouseExample example = new HouseExample();
        HouseExample.Criteria criteria = example.createCriteria();
        criteria.andIspassEqualTo(new Integer(1));

        List<House> list = houseMapper.selectByExample(example);

        PageInfo<House> pageInfo = new PageInfo<>(list);
        System.out.println(list.size());
        return pageInfo;
    }


    @Override
    public int passHouse(String id) {
        House house = new House();
        house.setId(id);
        house.setIspass(new Integer(1));
        int i = houseMapper.updateByPrimaryKeySelective(house);
        return i;
    }

    @Override
    public PageInfo<House> getHouseByBrowser(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getPageSize());
        //调用业务  Example只能实现单表条件搜索查询
        if(condition.getTitle()!=null) {
            condition.setTitle("%" + condition.getTitle() + "%");
        }

        List<House> list=houseMapper.getHouseByBrowser(condition);

        PageInfo<House> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}
