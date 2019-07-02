package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersExample;

import java.util.List;

public interface UsersService {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);


    PageInfo<Users> selectBypage(UserCondition userCondition);

    int checkUname(String username);


    int regUser(Users users);


    //登录验证
    Users regLogin(String username, String password);


}
