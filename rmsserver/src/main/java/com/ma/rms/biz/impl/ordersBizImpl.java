package com.ma.rms.biz.impl;

import com.ma.rms.biz.ordersBiz;
import com.ma.rms.dao.ordersDao;
import com.ma.rms.dao.impl.ordersDaoImpl;
import com.ma.rms.domain.orders;

public class ordersBizImpl implements ordersBiz{
	private ordersDao od;
	
	public ordersBizImpl() {
		od=new ordersDaoImpl();
	}

	public String insertOrders(orders o) {
		return this.od.insertOrders(o)?"添加成功":"添加失败";
	}

	public orders findOrdersById(String orid) {
		return od.selectOrdersById(orid);
	}

	public String deleteOrders(String orid) {
		return od.deleteOrders(orid)?"已取消订单":"删除失败";
	}

}
