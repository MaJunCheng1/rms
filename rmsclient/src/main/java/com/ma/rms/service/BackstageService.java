package com.ma.rms.service;

import java.util.List;

import com.ma.rms.domain.card;
import com.ma.rms.domain.employ;
import com.ma.rms.domain.menu;

public interface BackstageService {
	//添加员工
	String addEmp(employ e);
	//删除员工
	String deleteEmp(int id);
	//显示所有员工
	List<employ> findAllEmp();
	//显示员工信息
	employ findEmpById(int id);
	//补卡： 先查看原来的卡、再办一张新卡
	card fingCardById(int id);
	String newCad(card c);
	//冻结客户
	String coldCard(int carid,String status);
	//添加菜品:先选择菜品类型
	List<menu> selectFood(int typeid);
	String addMenu(menu m);
	//删除菜品
	String deleteMenu(int id);
	//修改菜品
	String updeteMenu(int id,String name,double pri,int tyid,String spec);
	//显示所有菜品
	List<menu> showAllMenu();
	//选择特价菜
	String selectSpecial(int id);
	//设置特价菜价格
	String setSpecialMenu(int id);
}
