package com.ma.rms.service;

import java.util.List;

import com.ma.rms.domain.card;
import com.ma.rms.domain.employ;
import com.ma.rms.domain.locate;
import com.ma.rms.domain.menu;
import com.ma.rms.domain.orderitem;
import com.ma.rms.domain.orders;

public interface ReceptionService {
	//登录
	employ entry(String accout,String password);
	//点菜的功能(往购物车里加)
	//显示所有菜品
		List<menu> selectAllFood();
		//根据typeid查找菜的类型
		String findtypename(int typeid);
		//根据meid查找菜
		menu selectFoodById(int meid);
		
		//添加订单
		String addOrders(orders o);
		//根据orid查找订单
		orders selectOrdersById(String orid);
		//删除订单
		String deleteOrders(String orid);
		
		//添加订单项
		String addOrderitem(orderitem oi);
		//修改订单项,并删除num小于定于0的菜
		String updateOrderitem(String orid,double num);
		//删除订单项
		String deleteOrderitem(String orid);
		//删除订单项(一个)
		String deleteOneOrderitem(String orid,int meid);
		//通过orid查询订单项
		List<orderitem> selectitem(String orid);
		//通过orid和meid找订单项
		orderitem selectOrderByoridandmeid(String orid,int meid);
		
		
		
		//根据地址编号查找地址
		locate selectLocateById(int locid);
		
	//结账(修改余额)
	String updateCard(int carid,double money);
	
	
	//根据卡号找卡
	card selectCardById(int carid);
	//开卡
	String openCard(card ca);
	//挂失
	boolean loss(int carid,String status);
	

	//充值
	String deposit(int carid,double balance);
}
