package com.ct.fruit.dao.impl;

import com.ct.fruit.dao.FruitDAO;
import com.ct.fruit.pojo.Fruit;
import com.ct.myssm.basedao.BaseDAO;

import java.util.List;

/**
* @Description: FruitDAO实现类，实现具体的操作。同时继承BaseDAO，可以使用里面的通用方法
* @Param:
* @return:
* @Author: CT
* @Date: 2022/1/6 11:01
*/
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    public FruitDAOImpl() throws ClassNotFoundException {
    }

    @Override
    public List<Fruit> getFruitList(String keyword,Integer pageNo) {
        return super.executeQuery("select * from t_fruit where fname like ? or remark like ? limit ? , 5","%"+keyword+"%","%"+keyword+"%",(pageNo-1)*5);
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return super.load("select * from t_fruit where fid = ?",fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql ="update t_fruit set fname = ?,price = ?,fcount = ? ,remark = ? where fid = ?";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        String sql ="delete from t_fruit where fid = ?";
        super.executeUpdate(sql,fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }

    @Override
    public int getFruitCount(String keyword) {
        return ((Long) super.executeComplexQuery("select count(*) from t_fruit where fname like ? or remark like ?","%"+keyword+"%","%"+keyword+"%")[0]).intValue();
    }
}
