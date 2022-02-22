package com.ct.fruit.service;

import com.ct.fruit.pojo.Fruit;

import java.util.List;

/**
* @Description: 定义对Fruit表的一系列事务操作规范，>DAO
* @Param:
* @return:
* @Author: CT
* @Date: 2022/1/6 11:03
*/
public interface FruitService {
    //获取指定页面的库存信息
    List<Fruit> getFruitList(String keyword,Integer pageNo);
    //添加库存记录
    void addFruit(Fruit fruit);
    //获取指定id的库存记录
    Fruit getFruitById(Integer fid);
    //删除库存记录
    void delFruit(Integer fid);
    //获取总页数
    Integer getPageCount(String keyword);
    //修改指定id的库存信息
    void updateFruit(Fruit fruit);
}
