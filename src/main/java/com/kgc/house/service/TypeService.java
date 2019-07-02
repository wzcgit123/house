package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;

import java.util.List;

public interface TypeService {
    PageInfo<Type> selectBypage(Integer page,Integer pageSize);


    int insertSelective(Type record);

    int upType(Type Type);


    int deleteByPrimaryKey(Integer id);


    int deleteMoreType(Integer[] ids);

    List<Type> selectBypage();

    List<Type> getAllType();
}
