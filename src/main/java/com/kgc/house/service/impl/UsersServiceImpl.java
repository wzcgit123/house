package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersExample;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.service.UsersService;
import com.kgc.house.util.MD5Utils;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersServiceImpl implements UsersService {


    @Autowired
    private UsersMapper usersMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Users record) {
        return 0;
    }

    @Override
    public int insertSelective(Users record) {
        return 0;
    }

    @Override
    public List<Users> selectByExample(UsersExample example) {
        return null;
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return 0;
    }


    @Override
    public PageInfo<Users> selectBypage(UserCondition userCondition) {
        PageHelper.startPage(userCondition.getPage(),userCondition.getrows());

        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andIsadminEqualTo(new Integer(1));
        if (userCondition.getName()!=null){
            criteria.andNameLike("%"+userCondition.getName()+"%");
        }
        if (userCondition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+userCondition.getTelephone()+"%");
        }
        List<Users> list = usersMapper.selectByExample(example);

        PageInfo<Users> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    //�鿴�û����Ƿ������
    @Override
    public int checkUname(String username) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        //��example���username������
        criteria.andNameEqualTo(username);

        List<Users> users = usersMapper.selectByExample(example);

        return  users.size()==0?0:1;
    }

    @Override
    public int regUser(Users users) {
        int i = usersMapper.insertSelective(users);
        return i;
    }


    @Override
    public Users regLogin(String username, String password) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();


        //��֤����  ����ȴ���������һ��
        criteria.andIsadminEqualTo(0);
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));



        //����dao�ɷ���

        List<Users> users = usersMapper.selectByExample(example);


        //������ڼ����ص�һ��  ��ֵֻ��Ϊ1
        if (users.size()==1){

            return users.get(0);

        }else {
            return null;
        }
    }




}
