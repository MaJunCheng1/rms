package com.ma.rms.test;

import java.util.List;

import org.junit.Test;

import com.ma.rms.dao.menuDao;
import com.ma.rms.dao.impl.menuDaoImpl;
import com.ma.rms.domain.menu;

public class MyTest {
//	@Test
	public void  findFoodById() {
		menuDao me=new menuDaoImpl();
		System.out.println(me.findFoodById(1));
	}
//	@Test
	public void  findAllFood() {
		menuDao me=new menuDaoImpl();
		List<menu> list = me.findAllFood();
		for (menu me2 : list) {
			System.out.println(me2.getMename());
		}
	}
	@Test
	public void insertFood() {
		menuDao me=new menuDaoImpl();
		me.insertFood(new menu(3, "鱼香肉丝", 15, 1, "是"));
	}
//	@Test
	public void deleteFood() {
		menuDao me=new menuDaoImpl();
		me.deleteFood(1);
	}
//	@Test
	public void updateFood() {
		menuDao me=new menuDaoImpl();
		me.updateFood(1, "麻婆豆腐", 9, 2, "否");
	}
}