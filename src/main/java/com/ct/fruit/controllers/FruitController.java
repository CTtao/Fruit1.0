package com.ct.fruit.controllers;

import com.ct.fruit.service.FruitService;
import com.ct.fruit.pojo.Fruit;
import com.ct.myssm.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
* @Description: 页面的具体事件
* @Param:
* @return:
* @Author: CT
* @Date: 2022/1/6 11:00
*/
public class FruitController {

    private FruitService fruitService = null;

    private String index(String oper,String keyword,Integer pageNo,HttpServletRequest req) {
        HttpSession session = req.getSession();

        if(pageNo==null){
            pageNo = 1;
        }
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            pageNo = 1;
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }else {
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj;
            }else {
                keyword = "";
            }
        }

        //更新当前页的pageNo到session
        session.setAttribute("pageNo",pageNo);

        List<Fruit> fruitList = fruitService.getFruitList(keyword,pageNo);
        session.setAttribute("fruitList",fruitList);

        //计算总页数并存入session
        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount",pageCount);

        return "index";
    }
    private String add(String fname,Integer price,Integer fcount,String remark) {
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitService.addFruit(fruit);
        return "redirect:fruit.do";
    }
    private String del(Integer fid) {
        if(fid != null){
            fruitService.delFruit(fid);
//            resp.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid,HttpServletRequest req) {
        if(fid!=null){
            Fruit fruit = fruitService.getFruitById(fid);
            req.setAttribute("fruit",fruit);
//            super.processTemplate("edit",req,resp);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark) {
        //执行更新
        fruitService.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //资源跳转
//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }
}
