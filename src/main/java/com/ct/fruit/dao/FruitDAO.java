package com.ct.fruit.dao;

import com.ct.fruit.pojo.Fruit;

import java.util.List;

/**
* @Description: FruitDAO接口，定义一系列对Fruit表的操作
* @Param:
* @return:
* @Author: CT
* @Date: 2022/1/6 11:01
*/
public interface FruitDAO {
    //获取所有的库存列表
    List<Fruit> getFruitList(String keyword,Integer pageNo);
    //通过fid获取水果库存
    Fruit getFruitById(Integer fid);
    //更新水果库存信息
    void updateFruit(Fruit fruit);

    //根据fid删除指定的记录
    void delFruit(Integer fid);

    //添加新记录
    void addFruit(Fruit fruit);

    //查询库存总条数
    int getFruitCount(String keyword);
}
