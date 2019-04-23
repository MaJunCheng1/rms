package com.ma.rms.test;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import com.ma.rms.dao.ordersDao;
import com.ma.rms.dao.impl.ordersDaoImpl;
import com.ma.rms.domain.orders;

public class OrdersTest {
//	@Test
	public void insertOrders() {
		ordersDao od=new ordersDaoImpl();
		String orid = UUID.randomUUID().toString().replace("-", "");
		System.out.println(orid);
		Date d = new Date();
		java.sql.Date date = new java.sql.Date(d.getTime());
		System.out.println(d);
		System.out.println(date);
		boolean b = od.insertOrders(new orders(orid, d, 1, 1, 1));
		System.out.println(b);
	}
//	@Test
	public void selectOrdersById() {
		ordersDao od=new ordersDaoImpl();
		orders o = od.selectOrdersById("ba14d6e472454deebb0f01f4cce300d0");
		System.out.println(o.getEid());
	}
	@Test
	public void delete1() {
		ordersDao od=new ordersDaoImpl();
		boolean b = od.deleteOrders("ba14d6e472454deebb0f01f4cce300d0");
		System.out.println(b);
	}
}
