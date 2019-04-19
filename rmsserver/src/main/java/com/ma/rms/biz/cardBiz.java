package com.ma.rms.biz;

import com.ma.rms.domain.card;

public interface cardBiz {
	//办卡服务
	String insertCard(card c);
	//结账修改余额
	String updateBalance(int carid,double balance);
	//修改卡的状态
	String updateStatus(int carid,String status);
}
