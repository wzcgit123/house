package com.kgc.house.entity;


//������ѯ
public class UserCondition {


    private Integer page;   //ҳ��
    private Integer rows;  //ҳ��С



    private String name;     //������ѯ����
    private String telephone;  //������ѯ�绰


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getrows() {
        return rows;
    }

    public void setrows(Integer rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
