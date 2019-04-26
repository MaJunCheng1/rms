package com.ma.rms.biz;

import java.util.List;

import com.ma.rms.domain.orderitem;

public interface orderitemBiz {
	// 添加订单项
	String insertOrderitem(orderitem oi);
	// 修改订单项
	String changeOrderitem(String orid,int meid, double num);
	//删除订单项
	String removeOrderitem();
	//删除订单项(一个)
	String removeOneOrderitem(String orid,int meid);
	// 通过orid查询订单项
	List<orderitem> finditem(String orid);
	// 通过orid和meid找订单项
	orderitem findOrderByoridandmeid(String orid, int meid);
	//查询所有订单项
	List<orderitem> findAllitem();
	//用meid进行分类查找每个菜的数量
	List<orderitem> findSumNum();
}
