package com.ma.rms.test;

import java.util.List;

import org.junit.Test;

import com.ma.rms.dao.menuDao;
import com.ma.rms.dao.impl.menuDaoImpl;
import com.ma.rms.domain.menu;
import com.ma.rms.domain.vegetType;

public class MenuTest {
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
//	@Test
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
//	@Test
	public void findtypename() {
		menuDao me=new menuDaoImpl();
		System.out.println(me.findtypename(1));
	}
//	@Test
	public void findFood() {
		menuDao me=new menuDaoImpl();
		List<menu> list = me.findFood(2);
		for (menu m : list) {
			System.out.println(m.getMename());
		}
	}
//	@Test
	public void f() {
		menuDao me=new menuDaoImpl();
		List<vegetType> list = me.showVegeType();
		for (vegetType ve : list) {
			System.out.println(ve.getId()+","+ve.getTypename());
		}
	}
}
