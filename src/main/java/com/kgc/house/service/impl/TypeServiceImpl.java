package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import com.kgc.house.mapper.TypeMapper;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.mapper.TypeMapper;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Type> selectBypage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        //给一个无参的对象
        TypeExample example = new TypeExample();
        List<Type> list = typeMapper.selectByExample(example);


        PageInfo<Type> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public int insertSelective(Type record) {
        return typeMapper.insertSelective(record);
    }



    @Transactional
    //添加事务 要么都成功 要么都失败
    public int deleteByPrimaryKey(Integer id) {


      return typeMapper.deleteByPrimaryKey(id);

    }

    @Override

    public int upType(Type Type) {

        return typeMapper.updateByPrimaryKeySelective(Type);

    }


    @Override

    public int deleteMoreType(Integer[] ids) {

        return typeMapper.deleteMoreType(ids);
    }


    @Override
    public List<Type> selectBypage() {
        return typeMapper.selectByExample(new TypeExample());
    }


    @Override
    public List<Type> getAllType() {
        TypeExample example = new TypeExample();
        List<Type> types = typeMapper.selectByExample(example);
        return types;
    }
}
