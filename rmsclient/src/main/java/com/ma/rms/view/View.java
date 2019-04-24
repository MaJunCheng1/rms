package com.ma.rms.view;

public class View {
	
	public void println(String s) {
		System.out.println(s);
	}
	public void welcom(){
		System.out.println("**********欢迎来到亚惠餐厅**********");
		System.out.println();
		System.out.println("*****请登录*****");
	}
	public void employ(){
		System.out.println("**********欢迎来到亚惠餐厅员工界面**********");
		System.out.println();
		System.out.println("1.点菜");
		System.out.println("2.开卡");
		System.out.println("3.挂失");
		System.out.println("4.解挂");
		System.out.println("5.充值");
		System.out.println("6.修改密码");
		System.out.println("0.退出");
	}
	public void emone() {
		System.out.println("1.添加菜");
		System.out.println("2.删除菜");
		System.out.println("3.显示所有菜");
	}
	public void manager(){
		System.out.println("**********欢迎来到亚惠餐厅经理界面**********");
		System.out.println();
		System.out.println("1.添加员工");
		System.out.println("2.删除员工");
		System.out.println("3.补卡");
		System.out.println("4.冻结客户");
		System.out.println("5.菜品管理");
		System.out.println("6.查看月销售量");
		System.out.println("7.查看最受欢迎的菜");
		System.out.println("8.查看返回意见");
		System.out.println("9.修改密码");
		System.out.println("10.添加公告");
		System.out.println("0.退出");
	}
	public void vegemana(){
		System.out.println("**********菜品管理界面**********");
		System.out.println();
		System.out.println("1.添加菜品");
		System.out.println("2.删除菜品");
		System.out.println("3.选择特价菜");
		System.out.println("4.显示所有菜品");
	}
}
