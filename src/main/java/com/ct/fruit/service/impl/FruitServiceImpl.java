package com.ct.fruit.service.impl;

import com.ct.fruit.dao.FruitDAO;
import com.ct.fruit.pojo.Fruit;
import com.ct.fruit.service.FruitService;
import com.ct.myssm.basedao.ConnUtil;

import java.util.List;

/**
* @Description: FruitService的实现类
* @Param:
* @return:
* @Author: CT
* @Date: 2022/1/6 11:03
*/
public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        System.out.println("getFruitList ->"+ConnUtil.getConn());
        return fruitDAO.getFruitList(keyword,pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return fruitDAO.getFruitById(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        System.out.println("getPageCount ->"+ConnUtil.getConn());
        int count = fruitDAO.getFruitCount(keyword);
        int pageCount = (count+5-1)/5;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
