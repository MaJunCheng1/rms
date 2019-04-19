package com.ma.rms.dao;

import com.ma.rms.domain.card;

public interface cardDao {
	//添加卡片信息
	boolean insertCard(card c);
	//修改卡片余额
	boolean updateBalance(int carid,double balance);
	//修改卡片状态
	boolean updateStatus(int carid,String status);
}
