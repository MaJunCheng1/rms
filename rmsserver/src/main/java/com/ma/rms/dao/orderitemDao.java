package com.ma.rms.dao;

import java.util.List;

import com.ma.rms.domain.orderitem;

public interface orderitemDao {
	// 添加订单项
	boolean addOrderitem(orderitem oi);
	// 修改订单项
	boolean updateOrderitem(String orid, double num);
	//删除订单项
	boolean deleteOrderitem(String orid);
	//删除订单项(一个)
	boolean deleteOneOrderitem(String orid,int meid);
	// 通过orid查询订单项
	List<orderitem> selectitem(String orid);
	// 通过orid和meid找订单项
	orderitem selectOrderByoridandmeid(String orid, int meid);
	//查询所有订单项
	List<orderitem> selectAllitem();
	//用meid进行分类查找每个菜的数量
	List<orderitem> selectSumNum();
}
