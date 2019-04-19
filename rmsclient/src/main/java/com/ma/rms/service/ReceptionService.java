package com.ma.rms.service;

import com.ma.rms.domain.card;
import com.ma.rms.domain.menu;

public interface ReceptionService {
	//登录
	String entry(String accout,String password);
	//点菜的功能(往购物车里加)
		//1加菜
		String addFoot(menu me);
		//2删除已存在的菜(从购物车里)
		String deleteFoot(int meid);
		//3查看购物车
		String  showCar();
	//结账
	String payment(int payid,int money);
	//开卡
	String openCard(card ca);
	//挂失
	String loss(int carid);
//	//打印小票
//	String print();
	//充值
	String deposit(int carid);
}
