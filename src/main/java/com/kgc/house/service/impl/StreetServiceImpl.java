package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Street record) {
        return 0;
    }

    @Override
    public int insertSelective(Street record) {
        return 0;
    }

    @Override
    public PageInfo<Street> selectByPage(Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);

        StreetExample example = new StreetExample();

        List<Street> list = streetMapper.selectByExample(example);

        PageInfo<Street> pageInfo =new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public Street selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Street record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Street record) {
        return 0;
    }

    @Override
    public int deleteStreetByDid(Integer Did) {
        return 0;
    }


    @Override
    public PageInfo<Street> selectByPage2(Integer page, Integer pageSize, Integer did) {

        PageHelper.startPage(page,pageSize);

        StreetExample example = new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(did);

        List<Street> list = streetMapper.selectByExample(example);

        PageInfo<Street> pageInfo =new PageInfo<>(list);

        return pageInfo;

    }


    @Override
    public List<Street> selectByPrimaryKey2(Integer did) {
        StreetExample example = new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(did);

        List<Street> list = streetMapper.selectByExample(example);
        return list;
    }


    @Override
    public List<Street> selectAllStreet() {
        StreetExample example = new StreetExample();

        List<Street> streets = streetMapper.selectByExample(example);

        return streets;
    }
}
