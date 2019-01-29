package com.nsfocus.extract;

import com.nsfocus.pojo.InfoModel;

/**
 * @Description 提取 地址 姓名 电话信息接口
 * @Author WH
 * @Date 2019/1/25 11:34
 **/
public interface Extractor {

    /**
     * @Description 提取 地址 姓名 电话信息接口
     * @Author wanghui
     * @Date 2019/1/25 14:03
     * @Param infoModel
     * @return void
     **/
    void extract(InfoModel infoModel);
}
