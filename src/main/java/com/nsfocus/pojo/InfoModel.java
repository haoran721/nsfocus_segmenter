package com.nsfocus.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/23 11:06
 **/
public class InfoModel extends BaseRowModel {
    @ExcelProperty(value = "序号", index = 0)
    private Integer id;
    @ExcelProperty(value = "发货信息", index = 1)
    private String sourceInfo;
    @ExcelProperty(value = "公司", index = 2)
    private String company;
    @ExcelProperty(value = "地址", index = 3)
    private String address;
    @ExcelProperty(value = "姓名", index = 4)
    private String name;
    @ExcelProperty(value = "电话", index = 5)
    private String phone;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(String sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "InfoModel{" +
                "id=" + id +
                ", sourceInfo='" + sourceInfo + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
