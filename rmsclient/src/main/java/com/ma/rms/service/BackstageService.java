package com.ma.rms.service;

import java.util.List;

import com.ma.rms.domain.employ;
import com.ma.rms.domain.menu;

public interface BackstageService {
	//添加普通员工
		String addEmp(employ em);
		//开除员工
		String deleteEmp(int eid);
		//补卡
		String replaceCard(int carid,int newId);
		//冻结卡
		String frozenCard(int carid);
		//菜品管理
			//根据id找菜
			menu selectFoodById(int meid);
			//添加菜品
			String addFood(menu m);
			//删除菜品
			String deleteFood(int meid);
			//选择特价菜
			String choice(int meid,String s);
			//显示所有菜品
			List<menu> showAllFood();
}
