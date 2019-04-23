package com.ma.rms.dao;

import com.ma.rms.domain.orders;

public interface ordersDao {
	//添加订单的功能
	boolean insertOrders(orders o);
	//根据orid查找订单
	orders selectOrdersById(String orid);
	//删除orders
	boolean deleteOrders(String orid);
}
