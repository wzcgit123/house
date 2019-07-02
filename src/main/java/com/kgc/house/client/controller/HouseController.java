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

    //���
    public String  addHouse(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session)  throws Exception{


        //�õ��ϴ��ļ�������

        String fname=pfile.getOriginalFilename();
        //�õ��ϴ��ļ��ĸ�ʽ
        String expName=fname.substring(fname.lastIndexOf("."));
        //�������������
        String saveName=System.currentTimeMillis()+expName; //�����ļ���
        //�ļ���ȫ·��
        File file=new File("E:\\images\\"+saveName);
        pfile.transferTo(file);  //����


        //���������houseid���� ��֤���ظ�
        house.setId(System.currentTimeMillis()+"");

        //��������λ�û��ϴ��ķ���  ��session�л�ȡ
        Users user = (Users) session.getAttribute("user");

        house.setUserId(user.getId());

        //�����Ƿ����  Ĭ��Ϊ0
        house.setIspass(0);
        //�����Ƿ�ɾ��
        house.setIsdel(0);

        //����path�������   ���ݷ����ļ���������  ���ݿ��ŵ����ļ�������
        house.setPath(saveName);

        //����ҵ��ɵķ����洢house������

        //��Ӱ�����ݴ���0ʱ ����ӳɹ� С��0ʱ�������ʧ��
        if ( houseService.add(house)>0){
            return "redirect:selectTypeAll2";
        }else {

            file.delete();
            return "redirect:selectTypeAll2";
        }



    }


//��ѯ����
    @RequestMapping("/selectHouse")

    public String  selectHouse(Model model,Integer page,HttpSession session ) {

        Users user = (Users) session.getAttribute("user");

        PageInfo<House> pageInfo =houseService.getUserHouseByPage(page==null?1:page,10,user.getId());

        model.addAttribute("pageInfo",pageInfo);

        return "guanli";
    }


    //��ѯ�޸�
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



    //�޸��ύ
    @RequestMapping("/upHouse")

    public String  upHouse(String oldPic,House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session )  throws Exception{
        //1.�޸�ʱ�ж��û���û���޸�ͼƬ
        File file=null;
        if(pfile.getOriginalFilename().equals("")){
            //System.out.println("���޸�ͼƬ");
            //����Ҫʵ���ļ��ϴ���ͬʱhouseʵ���path����������������
        }else {
            //System.out.println("�޸�ͼƬ");
            //�ϴ��µ�ͼƬ��ɾ���ɵ�ͼƬ������pathΪ�ϴ��µ�ͼƬ����
            file=new File("D:\\images\\"+oldPic);
            pfile.transferTo(file);  //����
            //����ͼƬ����
            house.setPath(oldPic);
        }

        //�������ݿ�ļ�¼  house�Ѿ����ղ��ֱ�����
        //���ñ��  �ӱ���ȡ
        //�������״̬ 0  ���������Ĭ��ֵ �ɲ���
        //house.setIspass(0);
        //�����Ƿ�ɾ��  0
        //house.setIsdel(0);
        //����ͼƬ·��
        // house.setPath(saveName);
        if(houseService.updateHouse(house)<=0){
            //�ɹ��ϴ���ͼƬɾ��
            if(file!=null) file.delete();
        }

        return "redirect:selectHouse";  //��ת����ѯ�û����ⷿ
    }





    //ɾ��
    @RequestMapping("/delHouse")

    public String  delHouse(String id ) {


        int temp=houseService.deleteHouse(id);


        return "redirect:selectHouse";
    }




    //��ѯ����������ⷿ��Ϣ
    @RequestMapping("goList")
    public String goList(HouseCondition condition, Model model){  //��ҳ��
        //����ҵ���ȡ���ⷿ
        PageInfo<House> pageInfo=houseService.getHouseByBrowser(condition);
        //����ҳ��Ϣ���õ���������
        model.addAttribute("pageInfo",pageInfo);

        if(condition.getTitle()!=null)
            condition.setTitle(condition.getTitle().replaceAll("%",""));
        model.addAttribute("condition",condition);  //���Բ�ѯ��
        return "list";
    }
}
