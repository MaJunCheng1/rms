package com.ma.rms.dao;

import java.util.List;

import com.ma.rms.domain.menu;
import com.ma.rms.domain.vegetType;

public interface menuDao {
	//根据meid查找该菜的信息
	menu findFoodById(int meid);
	//查找所有菜的信息
	List<menu> findAllFood();
	//增加菜品信息
	boolean insertFood(menu me);
	//删除菜品
	boolean deleteFood(int meid);
	//修改菜品信息
	boolean updateFood(int meid,String mename,double meprice,int typeid,String ifspecials);
	//通过typeid查找菜品信息
	List<menu> findFood(int typeid);
	//通过typeid查找typename
	String findtypename(int typeid);
	//选择特价菜
	boolean selectSpecial(int id);
	boolean setSpecial(int id);
	//显示所有菜的类型
	List<vegetType> showVegeType();
}
