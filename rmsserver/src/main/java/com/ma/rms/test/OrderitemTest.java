package com.ma.rms.test;

import java.util.List;

import org.junit.Test;

import com.ma.rms.dao.orderitemDao;
import com.ma.rms.dao.impl.orderitemDaoImpl;
import com.ma.rms.domain.orderitem;

public class OrderitemTest {
//	@Test
	public void addOrderitem() {
		orderitemDao od=new orderitemDaoImpl();
		boolean b = od.addOrderitem(new orderitem("ba14d6e472454deebb0f01f4cce300d0", 1, 2));
		System.out.println(b);
	}
//	@Test
	public void updateOrderitem() {
		orderitemDao od=new orderitemDaoImpl();
		boolean b = od.updateOrderitem("ba14d6e472454deebb0f01f4cce300d0", 1);
		System.out.println(b);
	}
//	@Test
	public void deleteOrderitem() {
		orderitemDao od=new orderitemDaoImpl();
		boolean b = od.deleteOrderitem();
		System.out.println(b);
	}
//	@Test
	public void deleteOneOrderitem() {
		orderitemDao od=new orderitemDaoImpl();
		boolean b = od.deleteOneOrderitem("ba14d6e472454deebb0f01f4cce300d0", 1);
		System.out.println(b);
	}
//	@Test
	public void selectitem() {
		orderitemDao od=new orderitemDaoImpl();
		List<orderitem> lsit = od.selectitem("ba14d6e472454deebb0f01f4cce300d0");
		for (orderitem o : lsit) {
			System.out.println(o.getOrid()+o.getNum()+o.getMeid());
		}
	}
	@Test
	public void selectOrderByoridandmeid() {
		orderitemDao od=new orderitemDaoImpl();
		 orderitem o = od.selectOrderByoridandmeid("eb5488a94c264dc18e5751be99befb", 1);
		 if(o==null) {
			 System.out.println("该菜品为空");
		 }else {
			 System.out.println(o.getMeid());
		 }
	}
}
