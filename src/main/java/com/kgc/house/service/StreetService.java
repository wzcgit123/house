package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;

import java.util.List;

public interface StreetService {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    PageInfo<Street> selectByPage(Integer page, Integer pageSize);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    int deleteStreetByDid(Integer Did);


    PageInfo<Street> selectByPage2(Integer page, Integer pageSize,Integer did);

    List<Street> selectByPrimaryKey2(Integer did);

    List<Street> selectAllStreet();

}
