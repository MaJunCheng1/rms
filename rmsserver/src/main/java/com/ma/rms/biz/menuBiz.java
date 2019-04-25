package com.ma.rms.biz;

import java.util.List;

import com.ma.rms.domain.menu;
import com.ma.rms.domain.vegetType;

public interface menuBiz {
	//根据meid查找该菜的信息
	menu selectFoodById(int meid);
	//查找所有菜的信息
	List<menu> selectAllFood();
	//增加菜品信息
	String addFood(menu me);
	//删除菜品
	String removeFood(int meid);
	//修改菜品信息
	String changeFood(int meid,String mename,double meprice,int typeid,String ifspecials);
	//通过typeid查找菜品信息
	List<menu> selectFood(int typeid);
	//通过typeid查找typename
	String selecttypename(int typeid);
	//选择特价菜
	String selectSpecial(String s,int id);
	String setSpecial(double price,int id);
	//显示所有菜
	List<vegetType> showAllType();
}
