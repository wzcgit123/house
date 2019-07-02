package com.kgc.house.entity;


//条件查询
public class UserCondition {


    private Integer page;   //页码
    private Integer rows;  //页大小



    private String name;     //条件查询名字
    private String telephone;  //条件查询电话


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
