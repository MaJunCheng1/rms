package com.ma.rms.service;

import java.util.List;

import com.ma.rms.domain.card;
import com.ma.rms.domain.employ;
import com.ma.rms.domain.locate;
import com.ma.rms.domain.menu;
import com.ma.rms.domain.orderitem;
import com.ma.rms.domain.orders;
import com.ma.rms.domain.vegetType;

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
		//删除订单项(num为0)
		String deleteOrderitem();
		//删除订单项(一个)
		String deleteOneOrderitem(String orid,int meid);
		//通过orid查询订单项
		List<orderitem> selectitem(String orid);
		//通过orid和meid找订单项
		orderitem selectOrderByoridandmeid(String orid,int meid);
		//查询所有订单项
		List<orderitem> selectAllitem();
		//用meid进行分类查找每个菜的数量
		List<orderitem> selectSumNum();
		
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
	//修改密码
	String updatePass(int eid,String newPass);
	
	
	
	
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
		String selectSpecial(String s,int id);
		//设置特价菜价格
		String setSpecialMenu(double price,int id);
		//显示所有菜的类型
		List<vegetType> showAllType();
		
		//生成菜单表
		public void generateMenuExcel();
		//生成订单项表
		public void generateOrderExcel();
		//生成菜品种类表
		public void generateTypeExcel();
}
