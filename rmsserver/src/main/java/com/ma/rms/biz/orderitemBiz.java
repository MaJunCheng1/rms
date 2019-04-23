package com.ma.rms.biz;

import java.util.List;

import com.ma.rms.domain.orderitem;

public interface orderitemBiz {
	// 添加订单项
	String insertOrderitem(orderitem oi);
	// 修改订单项
	String changeOrderitem(String orid, double num);
	//删除订单项
	String removeOrderitem(String orid);
	//删除订单项(一个)
	String removeOneOrderitem(String orid,int meid);
	// 通过orid查询订单项
	List<orderitem> finditem(String orid);
	// 通过orid和meid找订单项
	orderitem findOrderByoridandmeid(String orid, int meid);
}
