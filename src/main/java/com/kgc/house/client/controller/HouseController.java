package com.kgc.house.client.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.*;
import com.kgc.house.service.DistrictServie;
import com.kgc.house.service.HouseService;

import com.kgc.house.service.StreetService;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.http.HttpSession;
import java.io.File;

import java.util.List;


@Controller
public class HouseController {


    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictServie districtServie;
    @Autowired
   private HouseService houseService;
    @Autowired
    private StreetService streetService;




    @RequestMapping("/selectTypeAll2")

    public String  selectAll2(Model model) {

     List<Type> types =typeService.selectBypage();

     List<District> districts=districtServie.selectBypage2();

     model.addAttribute("types",types);

      model.addAttribute("districts",districts);


        return "fabu";
    }



    @RequestMapping("/addHouse")

    //添加
    public String  addHouse(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session)  throws Exception{


        //得到上传文件的名称

        String fname=pfile.getOriginalFilename();
        //得到上传文件的格式
        String expName=fname.substring(fname.lastIndexOf("."));
        //创建随机的名称
        String saveName=System.currentTimeMillis()+expName; //保存文件名
        //文件的全路径
        File file=new File("E:\\images\\"+saveName);
        pfile.transferTo(file);  //保存


        //放入随机的houseid名称 保证不重复
        house.setId(System.currentTimeMillis()+"");

        //放入是哪位用户上传的房子  从session中获取
        Users user = (Users) session.getAttribute("user");

        house.setUserId(user.getId());

        //设置是否审核  默认为0
        house.setIspass(0);
        //设置是否删除
        house.setIsdel(0);

        //设置path存放数据   数据放在文件服务器上  数据库存放的是文件的名称
        house.setPath(saveName);

        //调用业务成的方法存储house的数据

        //当影响数据大于0时 则添加成功 小于0时就是添加失败
        if ( houseService.add(house)>0){
            return "redirect:selectTypeAll2";
        }else {

            file.delete();
            return "redirect:selectTypeAll2";
        }



    }


//查询房子
    @RequestMapping("/selectHouse")

    public String  selectHouse(Model model,Integer page,HttpSession session ) {

        Users user = (Users) session.getAttribute("user");

        PageInfo<House> pageInfo =houseService.getUserHouseByPage(page==null?1:page,10,user.getId());

        model.addAttribute("pageInfo",pageInfo);

        return "guanli";
    }


    //查询修改
    @RequestMapping("/getUpHouse")

    public String  getUpHouse(Model model,String id ) {

        List<District> districts = districtServie.selectBypage2();

        List<Type> types=typeService.getAllType();


        House house=houseService.selectById(id);

        model.addAttribute("districts",districts);

        model.addAttribute("types",types);

        model.addAttribute("house",house);


        return "upfabu";
    }



    //修改提交
    @RequestMapping("/upHouse")

    public String  upHouse(String oldPic,House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session )  throws Exception{
        //1.修改时判断用户有没有修改图片
        File file=null;
        if(pfile.getOriginalFilename().equals("")){
            //System.out.println("不修改图片");
            //不需要实现文件上传，同时house实体的path属性无需设置属性
        }else {
            //System.out.println("修改图片");
            //上传新的图片，删除旧的图片，设置path为上传新的图片名称
            file=new File("D:\\images\\"+oldPic);
            pfile.transferTo(file);  //保存
            //设置图片名称
            house.setPath(oldPic);
        }

        //保存数据库的记录  house已经接收部分表单数据
        //设置编号  从表单获取
        //设置审核状态 0  如果表中有默认值 可不设
        //house.setIspass(0);
        //设置是否删除  0
        //house.setIsdel(0);
        //设置图片路径
        // house.setPath(saveName);
        if(houseService.updateHouse(house)<=0){
            //成功上传的图片删除
            if(file!=null) file.delete();
        }

        return "redirect:selectHouse";  //跳转到查询用户出租房
    }





    //删除
    @RequestMapping("/delHouse")

    public String  delHouse(String id ) {


        int temp=houseService.deleteHouse(id);


        return "redirect:selectHouse";
    }




    //查询所有浏览出租房信息
    @RequestMapping("goList")
    public String goList(HouseCondition condition, Model model){  //传页码
        //调用业务获取出租房
        PageInfo<House> pageInfo=houseService.getHouseByBrowser(condition);
        //将分页信息设置到作用域中
        model.addAttribute("pageInfo",pageInfo);

        if(condition.getTitle()!=null)
            condition.setTitle(condition.getTitle().replaceAll("%",""));
        model.addAttribute("condition",condition);  //回显查询条
        return "list";
    }
}
