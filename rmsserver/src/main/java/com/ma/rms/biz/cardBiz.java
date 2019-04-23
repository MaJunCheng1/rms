package com.ma.rms.biz;

import com.ma.rms.domain.card;

public interface cardBiz {
	//办卡服务
	String insertCard(card c);
	//结账修改余额
	String updateBalance(int carid,double balance);
	//修改卡的状态
	boolean updateStatus(int carid,String status);
	// 根据卡号找卡
	card findCardById(int carid);
	// 充值
	String deposit(int carid, double balance);
}
