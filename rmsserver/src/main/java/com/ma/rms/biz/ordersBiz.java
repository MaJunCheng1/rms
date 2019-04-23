package com.ma.rms.biz;

import com.ma.rms.domain.orders;

public interface ordersBiz {
	//添加订单的功能
	public String insertOrders(orders o);
	//根据orid查找订单
	orders findOrdersById(String orid);
	//删除orders
	String deleteOrders(String orid);
}
