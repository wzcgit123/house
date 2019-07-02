package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.DistrictServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DistrictServieImpl implements DistrictServie {



    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<District> selectBypage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        //��һ���޲εĶ���
        DistrictExample example = new DistrictExample();
        List<District> list = districtMapper.selectByExample(example);

        PageInfo<District> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public int insertSelective(District record) {
        return districtMapper.insertSelective(record);
    }



    @Transactional
    //������� Ҫô���ɹ� Ҫô��ʧ��
    public int deleteByPrimaryKey(Integer id) {



        try {
            //���ýֵ��Ĳ�ѯ���
            streetMapper.deleteStreetByDid(id);
            districtMapper.deleteByPrimaryKey(id);

            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    @Override

    public int upDistrict(District district) {

        return districtMapper.updateByPrimaryKeySelective(district);

  }


    @Override

    public int deleteMoreDistrict(Integer[] ids) {

        return districtMapper.deleteMoreDistrict(ids);
    }


    @Override
    public List<District> selectBypage2() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
