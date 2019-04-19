package com.ma.rms.view;

import org.junit.Test;

public class view {
	public void welcom(){
		System.err.println("❤❤❤❤❤❤❤❤❤❤欢迎来到亚惠餐厅❤❤❤❤❤❤❤❤❤❤");
		System.out.println();
		System.out.println("*****请登录**");
	}
	@Test
	public void employ(){
		System.err.println("❤❤❤❤❤❤❤❤❤❤欢迎来到亚惠餐厅员工管理界面❤❤❤❤❤❤❤❤❤❤");
		System.out.println();
		System.out.println("1.点菜❤");
		System.out.println("2.结账❤");
		System.out.println("3.开卡❤");
		System.out.println("4.挂失❤");
		System.out.println("5.解挂❤");
		System.out.println("6.充值❤");
	}
	public void manager(){
		System.err.println("❤❤❤❤❤❤❤❤❤❤欢迎来到亚惠餐厅经理管理界面❤❤❤❤❤❤❤❤❤❤");
		System.out.println();
		System.out.println("1.添加员工");
		System.out.println("2.删除员工");
		System.out.println("3.补卡❤❤");
		System.out.println("4.冻结客户");
		System.out.println("5.菜品管理");
	}
	public void vegemana(){
		System.err.println("❤❤❤❤❤❤❤❤❤❤菜品管理界面❤❤❤❤❤❤❤❤❤❤");
		System.out.println();
		System.out.println("1.添加菜品❤❤");
		System.out.println("2.删除菜品❤❤");
		System.out.println("3.选择特价菜❤");
		System.out.println("4.显示所有菜品");
	}
}
